package tools.ttv.mail

import tools.ttv.mail.restapi.RestAPIMailManager
import tools.ttv.mail.restapi.model.Mail
import java.io.IOException
import java.util.function.Consumer

class RestAPIMailService {
    private val restAPIMailManager = RestAPIMailManager()
    fun createEmail(mailAddress: Consumer<String>, incomingMail: Consumer<Mail>, createEmailErrorConsumer: Consumer<IOException>) {
        restAPIMailManager.createMail(mailAddress, incomingMail, createEmailErrorConsumer)
    }
}
