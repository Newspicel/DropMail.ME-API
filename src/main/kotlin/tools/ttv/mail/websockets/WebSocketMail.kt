package tools.ttv.mail.websockets

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class WebSocketMail(
        val toMailOrig: String,
        val toMail: String,
        val textSource: String,
        val text: String,
        val subject: String,
        val ref: String,
        val received: Long,
        val hasHtml: Boolean,
        val fromMail: String,
        val fromHdr: String
) {
    companion object {

        private val gsonWebSocket: Gson = GsonBuilder().registerTypeAdapter(WebSocketMail::class.java, WebSocketMailJsonDeserializer()).create()

        fun decode(string: String): WebSocketMail {
            return gsonWebSocket.fromJson(string, WebSocketMail::class.java)
        }
    }

}

