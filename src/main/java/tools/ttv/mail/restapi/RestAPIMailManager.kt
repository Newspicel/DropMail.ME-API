package tools.ttv.mail.restapi;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import tools.ttv.mail.websockets.WebSocketMail;

import java.util.HashMap;
import java.util.function.Consumer;

public class RestAPIMailManager {

    private static String token = "qwefoliihouiqwhneofikjjnqwoin";

    public static final MediaType JSON  = MediaType.get("application/json; charset=utf-8");
    public static final MediaType X_WWW_FORM  = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();


    private HashMap<String, Consumer<WebSocketMail>> mailConsumers = new HashMap<>();


}
