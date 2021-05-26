package tools.ttv.mail.restapi

import com.google.gson.GsonBuilder
import lombok.Getter
import okhttp3.*
import tools.ttv.mail.restapi.model.Mail
import tools.ttv.mail.restapi.CreateEmailRequest
import java.io.IOException
import kotlin.Throws
import tools.ttv.mail.restapi.RestAPIMailRunnable
import java.util.*
import java.util.function.Consumer

class RestAPIMailManager {
    val client = OkHttpClient()

    val gson = GsonBuilder().create()!!

    val mailConsumers = HashMap<String, Consumer<Mail>>()
    fun createMail(email: Consumer<String>, incomingMail: Consumer<Mail>) {
        initSession { createEmailRequest: CreateEmailRequest ->
            val introduceSession = createEmailRequest.data.introduceSession
            email.accept(introduceSession.addresses[0].address)
            mailConsumers[introduceSession.id] = incomingMail
        }
    }

    private fun initSession(createEmailRequestConsumer: Consumer<CreateEmailRequest>) {
        val request: Request = Request.Builder()
            .url("https://dropmail.me/api/graphql/$TOKEN?query=mutation%20%7BintroduceSession%20%7Bid%2C%20expiresAt%2C%20addresses%20%7Baddress%7D%7D%7D")
            .get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                response.body.use { responseBody ->
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    createEmailRequestConsumer.accept(gson.fromJson(Objects.requireNonNull(responseBody)!!.string(), CreateEmailRequest::class.java))
                }
            }
        })
    }

    companion object {
        val TOKEN = UUID.randomUUID().toString()
    }

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(RestAPIMailRunnable(this), 2000, 2000)
    }
}
