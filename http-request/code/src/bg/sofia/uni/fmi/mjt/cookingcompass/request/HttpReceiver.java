package bg.sofia.uni.fmi.mjt.cookingcompass.request;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public interface HttpReceiver {

    String send(HttpRequest request, HttpClient client);

}
