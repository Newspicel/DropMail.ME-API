package tools.ttv.mail.restapi.model

class Address(var address: String)
class IntroduceSession(var id: String, var addresses: List<Address>)
class CreateMailData(var introduceSession: IntroduceSession)
class CreateEmailRequest(var data: CreateMailData)
