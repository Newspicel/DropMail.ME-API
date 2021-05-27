package tools.ttv.mail

import tools.ttv.mail.websockets.WebSocketMail
import tools.ttv.mail.websockets.WebSocketsMailManager
import java.util.function.Consumer

class WebSocketMailService {

    private val webSocketsMailManager: WebSocketsMailManager = WebSocketsMailManager()

    init {
        webSocketsMailManager.init()
    }

    fun createAndGetMail(webSocketMailConsumer: Consumer<WebSocketMail>): String {
        val mail = webSocketsMailManager.getMail()
        webSocketsMailManager.receiveMessage(mail, webSocketMailConsumer)
        return mail
    }

}
