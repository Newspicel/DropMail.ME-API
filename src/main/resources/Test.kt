import tools.ttv.mail.RestAPIMailService
import tools.ttv.mail.restapi.model.Mail
import kotlin.jvm.JvmStatic

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val restAPIMailService: RestAPIMailService = RestAPIMailService()

        for(i in 0..1000){
            restAPIMailService.createEmail({ s -> println(s) }, { t: Mail -> println(t.downloadUrl) })
        }





    }
}
