package bg.sofia.uni.fmi.mjt.cookingcompass.request;

import bg.sofia.uni.fmi.mjt.cookingcompass.recipe.Recipe;
import bg.sofia.uni.fmi.mjt.cookingcompass.recipe.RecipePage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

public class RecipeRetriever implements RecipePageConverter {

    private RequestMaker requestMaker;

    public RecipeRetriever(RequestMaker requestMaker) {
        this.requestMaker = requestMaker;
    }

    public List<Recipe> retrieve() {

        List<RecipePage> pages = new LinkedList<>();
        RecipePage current = convertResponse(requestMaker.getPage());
        pages.add(current);

        while (current.hasNextPage()) {
            try {
                requestMaker.setRequest(new URI(current.getNextPage().toString()));
                current = convertResponse(requestMaker.getPage());
                pages.add(current);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return convertWrapper(pages);
    }

    @Override
    public RecipePage convertResponse(String response) {
        Gson gson = new Gson();
        RecipePage recipePage = gson.fromJson(response, new TypeToken<RecipePage>() { }
                .getType());
        return recipePage;
    }

    public List<Recipe> convertWrapper(List<RecipePage> pages) {
        if (pages == null) {
            throw new IllegalArgumentException("No pages at all.");
        }

        List<Recipe> retVal = new LinkedList<Recipe>();
        for (RecipePage page: pages) {
            retVal.addAll(page.getRecipes());
        }
        return retVal;
    }
}
