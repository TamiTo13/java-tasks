package bg.sofia.uni.fmi.mjt.cookingcompass.request.uri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;

public class URICreatorTest {

    @Test
    void testCorrectURI() {
        URICreator uriCreator = new URICreator(
                List.of("pizza", "tomato", "onion"),
                List.of("Breakfast"),
                List.of("alcohol-free", "dairy-free"));

        URICreator uriCreator1 = new URICreator(
                List.of("pizza", "tomato", "onion"),
                null,
                List.of("alcohol-free", "dairy-free"));

        URICreator uriCreator2 = new URICreator(
                List.of("pizza", "tomato", "onion"),
                List.of("Breakfast"),
                null);

        try {
            Assertions.assertEquals(uriCreator1.getUri(),
                    new URI("https", "api.edamam.com",
                            "/api/recipes/v2?type=public&" +
                                    "q=pizza%20tomato%20onion&" +
                                    "app_id=09237e92&app_key=4eb81613c21b788d0b6bc30303d3add0&" +
                                    "health=alcohol-free&health=dairy-free", null));

            Assertions.assertEquals(uriCreator.getUri(),
                    new URI("https", "api.edamam.com",
                            "/api/recipes/v2?type=public&" +
                                    "q=pizza%20tomato%20onion&" +
                                    "app_id=09237e92&app_key=4eb81613c21b788d0b6bc30303d3add0&" +
                                    "health=alcohol-free&health=dairy-free&mealType=Breakfast", null));

            Assertions.assertEquals(uriCreator2.getUri(),
                    new URI("https", "api.edamam.com",
                            "/api/recipes/v2?type=public&" +
                                    "q=pizza%20tomato%20onion&" +
                                    "app_id=09237e92&app_key=4eb81613c21b788d0b6bc30303d3add0&" +
                                    "mealType=Breakfast", null));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
