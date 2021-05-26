package tools.ttv.mail.restapi;

import okhttp3.RequestBody;

import java.util.TimerTask;

public class RestAPIMailRunnable extends TimerTask {

    private final RestAPIMailManager restAPIMailManager;

    public RestAPIMailRunnable(RestAPIMailManager restAPIMailManager) {
        this.restAPIMailManager = restAPIMailManager;
    }

    @Override
    public void run() {

    }

    private void requestEmails() {

    }
}
