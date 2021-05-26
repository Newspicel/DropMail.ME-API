package tools.ttv.mail.restapi

class Address(var address: String)
class IntroduceSession(var id: String, var addresses: List<Address>)
class Data(var introduceSession: IntroduceSession)
class CreateEmailRequest(var data: Data)
