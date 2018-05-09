package space.qyvlik.jmeter.hello;

import com.github.kevinsawicki.http.HttpRequest;


public class UseCaseTester {


    public UseCaseTester() {

    }

    public String get() {
        try {
            return HttpRequest.get("https://api.github.com/zen").body();
//            return "";
        } catch (Exception e) {
            return "failure";
        }
    }

}
