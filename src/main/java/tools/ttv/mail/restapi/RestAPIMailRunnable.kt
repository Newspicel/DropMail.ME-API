package tools.ttv.mail.restapi

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import tools.ttv.mail.restapi.model.GetEmailModel
import tools.ttv.mail.restapi.model.Mail
import java.io.IOException
import java.util.*
import java.util.function.Consumer

class RestAPIMailRunnable(private val restAPIMailManager: RestAPIMailManager) : TimerTask() {
    override fun run() {
        requestEmails()
    }

    private fun requestEmails() {
        val request: Request = Request.Builder()
            .url("https://dropmail.me/api/graphql/" + RestAPIMailManager.TOKEN + "?query=query%20%7Bsessions%20%7Bid%2C%20expiresAt%2C%20mails%20%7BrawSize%2C%20fromAddr%2C%20toAddr%2C%20downloadUrl%2C%20text%2C%20headerSubject%7D%7D%7D")
            .get()
            .build()
        restAPIMailManager.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body.use { responseBody ->
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    restAPIMailManager.gson.fromJson(responseBody!!.string(), GetEmailModel::class.java)
                        .data
                        .sessions
                        .forEach { session ->
                            restAPIMailManager
                                .mailConsumers
                                .filter { entry: Map.Entry<String, Consumer<Mail>> -> entry.key == session.id }
                                .forEach { stringConsumerEntry ->
                                    if (session.mails.isNotEmpty()) {
                                        stringConsumerEntry.value.accept(session.mails[0])
                                        restAPIMailManager.mailConsumers.remove(stringConsumerEntry.key)
                                    }
                                }
                        }
                }
            }
        })
    }
}
