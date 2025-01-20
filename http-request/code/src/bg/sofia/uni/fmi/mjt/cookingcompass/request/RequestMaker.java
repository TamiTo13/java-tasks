package bg.sofia.uni.fmi.mjt.cookingcompass.request;

import bg.sofia.uni.fmi.mjt.cookingcompass.request.uri.URICreator;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

public class RequestMaker {

    private HttpRequest request;
    private HttpClient client;

    private HttpReceiver receiver;

    public RequestMaker(List<String> keywords, List<String> mealTypes, List<String> dietType, HttpReceiver receiver) {
        URICreator uriCreator = new URICreator(keywords, mealTypes, dietType);
        request = HttpRequest.newBuilder().uri(uriCreator.getUri()).build();
        client = HttpClient.newBuilder().build();
        this.receiver = receiver;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(URI link) {
        request = HttpRequest.newBuilder().uri(link).build();
    }

    public HttpClient getClient() {
        return client;
    }

    public String getPage() {
        return this.receiver.send(request, client);
    }
}
