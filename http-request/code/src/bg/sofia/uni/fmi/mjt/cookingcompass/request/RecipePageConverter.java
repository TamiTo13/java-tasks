package bg.sofia.uni.fmi.mjt.cookingcompass.request;

import bg.sofia.uni.fmi.mjt.cookingcompass.recipe.RecipePage;

public interface RecipePageConverter {

    RecipePage convertResponse(String response);

}
