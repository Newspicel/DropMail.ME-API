package tools.ttv.mail.websockets

import com.google.gson.*
import java.lang.reflect.Type

class WebSocketMailJsonDeserializer : JsonDeserializer<WebSocketMail> {


    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): WebSocketMail {
        val jsonObject: JsonObject = json.asJsonObject
        return WebSocketMail(
                jsonObject.get("to_mail_orig").asString,
                jsonObject.get("to_mail").asString,
                jsonObject.get("text_source").asString,
                jsonObject.get("text").asString,
                jsonObject.get("subject").asString,
                jsonObject.get("ref").asString,
                System.currentTimeMillis(),
                jsonObject.get("has_html").asBoolean,
                jsonObject.get("from_mail").asString,
                jsonObject.get("from_hdr").asString
        )
    }
}
