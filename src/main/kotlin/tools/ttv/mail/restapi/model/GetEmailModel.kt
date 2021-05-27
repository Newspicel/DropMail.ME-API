package tools.ttv.mail.restapi.model

class Mail(var toAddr: String, var text: String, var rawSize: Int, var headerSubject: String, var fromAddr: String, var downloadUrl: String)
class Session(var mails: List<Mail>, var id: String)
class GetMailData(var sessions: List<Session>)
class GetEmailModel(var data: GetMailData)
