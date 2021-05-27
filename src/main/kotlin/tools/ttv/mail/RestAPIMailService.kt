package tools.ttv.mail

import tools.ttv.mail.restapi.RestAPIMailManager
import tools.ttv.mail.restapi.model.Mail
import java.util.function.Consumer

class RestAPIMailService {
    private val restAPIMailManager = RestAPIMailManager()
    fun createEmail(mailAddress: Consumer<String>, incomingMail: Consumer<Mail>) {
        restAPIMailManager.createMail(mailAddress, incomingMail)
    }
}
