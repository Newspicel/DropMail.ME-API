
import tools.ttv.mail.RestAPIMailService
import tools.ttv.mail.restapi.model.Mail

object Example {

    @JvmStatic
    fun main(args: Array<String>) {
        val restAPIMailService = RestAPIMailService()
        restAPIMailService.createEmail({ s -> println(s) }, { t: Mail -> println(t.downloadUrl) }, { err -> err.printStackTrace() })
    }
}
