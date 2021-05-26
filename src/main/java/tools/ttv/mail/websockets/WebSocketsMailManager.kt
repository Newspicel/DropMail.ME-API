package tools.ttv.mail.websockets

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.function.Consumer

class WebSocketsMailManager {

    private val webSocketMailListener: WebSocketMailListener = WebSocketMailListener(this)

    val webSocketMailUnits: HashMap<String, Consumer<WebSocketMail>> = HashMap()
    val mailPool: ArrayList<String> = ArrayList()

    private lateinit var webSocket: WebSocket
    private lateinit var okHttpClient: OkHttpClient


    fun init() {
        okHttpClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build()
        val request: Request = Request.Builder()
                .url(URL)
                .build()
        webSocket = okHttpClient.newWebSocket(request, webSocketMailListener)
    }

    fun getMail() : String {
        webSocket.send("M")
        return mailPool.removeFirst()
    }

    @Suppress("UNUSED_EXPRESSION")
    fun receiveMessage(email: String, consumer: Consumer<WebSocketMail>) {
        webSocketMailUnits[email] = consumer
    }

    companion object {
        private const val URL = "wss://dropmail.me/websocket"
    }


}
