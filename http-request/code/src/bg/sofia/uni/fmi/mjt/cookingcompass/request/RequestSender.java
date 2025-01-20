package bg.sofia.uni.fmi.mjt.cookingcompass.request;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestSender implements HttpReceiver {

    @Override
    public String send(HttpRequest request, HttpClient client) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
