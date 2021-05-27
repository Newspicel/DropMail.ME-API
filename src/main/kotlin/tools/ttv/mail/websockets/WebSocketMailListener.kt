package tools.ttv.mail.websockets

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.function.Consumer

class WebSocketMailListener(private val webSocketsMailManager: WebSocketsMailManager) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        for (i in 0..10) {
            webSocket.send("M")
        }
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        val context: String = text.substring(1)
        when (text.elementAt(0)) {
            'I' -> {
                val webSocketMail: WebSocketMail = WebSocketMail.decode(context)
                webSocketsMailManager.webSocketMailUnits
                        .filter { entry: Map.Entry<String, Consumer<WebSocketMail>> -> entry.key == webSocketMail.toMailOrig }
                        .map { entry: Map.Entry<String, Consumer<WebSocketMail>> -> entry.value }
                        .forEach { consumer: Consumer<WebSocketMail> -> consumer.accept(webSocketMail) }
            }
            'A' -> webSocketsMailManager.mailPool.add(context.split(":")[0])
        }
    }
}
