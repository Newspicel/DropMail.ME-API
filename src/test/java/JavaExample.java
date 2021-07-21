import tools.ttv.mail.RestAPIMailService;

public class JavaExample {

    public static void main(String[] args) {
        RestAPIMailService restAPIMailService = new RestAPIMailService();
        restAPIMailService.createEmail(System.out::println, mail -> System.out.println(mail.getDownloadUrl()), Throwable::printStackTrace);
    }
}
