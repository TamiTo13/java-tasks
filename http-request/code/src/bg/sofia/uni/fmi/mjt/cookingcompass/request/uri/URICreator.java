package bg.sofia.uni.fmi.mjt.cookingcompass.request.uri;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class URICreator {
    private static final String APP_ID = "app_id=09237e92";
    private static final String APP_KEY = "app_key=4eb81613c21b788d0b6bc30303d3add0";
    private static final String HOST = "api.edamam.com";
    private static final String PATH = "/api/recipes/v2?type=public&";
    private static final String FORMAT = "https";

    private static final String SEPARATOR = "&";
    private static final String KEYWORD_SEPARATOR = "%20";

    private static final String KEYWORD_PREFIX = "q=";
    private static final String MEAL_TYPE_PREFIX = "mealType=";
    private static final String HEALTH_PREFIX = "health=";

    private URI uri;

    public URICreator(List<String> keywords, List<String> mealTypes, List<String> health) {
        try {
            this.uri = new URI(FORMAT, HOST, createComponents(keywords, mealTypes, health), null);
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    public URI getUri() {
        return uri;
    }

    private String createComponents(List<String> keywords, List<String> mealTypes, List<String> health ) {
        StringBuilder retVal = new StringBuilder(PATH);

        attachKeywords(retVal, keywords);
        attachIdAndKey(retVal);
        attachHealthTags(retVal, health);
        attachMealTypes(retVal, mealTypes);

        return retVal.toString();
    }

    private void attachKeywords(StringBuilder retVal, List<String> keywords) {
        if (keywords != null) {
            retVal.append(KEYWORD_PREFIX + keywords.getFirst());
            for (int i = 1; i < keywords.size(); i++) {
                retVal.append(KEYWORD_SEPARATOR + keywords.get(i));
            }
            retVal.append(SEPARATOR);
        }
    }

    private static void attachIdAndKey(StringBuilder builder) {
        builder.append(APP_ID + SEPARATOR + APP_KEY);
    }

    private void attachMealTypes(StringBuilder retVal, List<String> mealTypes) {
        if (mealTypes != null) {
            retVal.append(SEPARATOR + MEAL_TYPE_PREFIX + mealTypes.getFirst());
            for (int i = 1; i < mealTypes.size(); i++) {
                retVal.append(SEPARATOR + MEAL_TYPE_PREFIX + mealTypes.get(i));
            }
        }
    }

    private void attachHealthTags(StringBuilder retVal, List<String> healthTags) {
        if (healthTags != null) {
            retVal.append(SEPARATOR + HEALTH_PREFIX + healthTags.getFirst());
            for (int i = 1; i < healthTags.size(); i++) {
                retVal.append(SEPARATOR + HEALTH_PREFIX + healthTags.get(i));
            }
        }
    }

}
