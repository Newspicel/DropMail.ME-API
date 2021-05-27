
import tools.ttv.mail.RestAPIMailService
import tools.ttv.mail.restapi.model.Mail

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val restAPIMailService = RestAPIMailService()

        for(i in 0..1000){
            restAPIMailService.createEmail({ s -> println(s) }, { t: Mail -> println(t.downloadUrl) })
        }
    }
}
