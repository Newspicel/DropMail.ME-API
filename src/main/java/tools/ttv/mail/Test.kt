package tools.ttv.mail

import tools.ttv.mail.websockets.WebSocketMail
import kotlin.jvm.JvmStatic

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val webSocketMailService: WebSocketMailService = WebSocketMailService()

        Thread.sleep(1000)

        val mailAddress = webSocketMailService.createAndGetMail { x: WebSocketMail -> println(x) }
        println(mailAddress)
    }
}
