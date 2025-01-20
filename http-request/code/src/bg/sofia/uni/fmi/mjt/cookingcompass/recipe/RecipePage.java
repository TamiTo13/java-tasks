package bg.sofia.uni.fmi.mjt.cookingcompass.recipe;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class RecipePage {

    public List<Hit> hits;

    @SerializedName("_links")
    public JsonElement links;

    public LinkedList<Recipe> getRecipes() {
        LinkedList<Recipe> retVal = new LinkedList<>();
        if (hits != null) {
            for (Hit recipe: hits) {
                retVal.add(recipe.recipe());
            }
        }
        return retVal;
    }

    public JsonElement getNextPage() {

        return links.getAsJsonObject().get("next").getAsJsonObject().get("href");
    }

    public boolean hasNextPage() {
        return links != null && !links.toString().equals("{}");
    }
}
