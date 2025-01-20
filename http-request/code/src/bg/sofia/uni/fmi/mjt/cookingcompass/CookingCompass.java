package bg.sofia.uni.fmi.mjt.cookingcompass;

import bg.sofia.uni.fmi.mjt.cookingcompass.exception.IncorrectHealthException;
import bg.sofia.uni.fmi.mjt.cookingcompass.exception.IncorrectMealTypeException;
import bg.sofia.uni.fmi.mjt.cookingcompass.recipe.Recipe;
import bg.sofia.uni.fmi.mjt.cookingcompass.request.RecipeRetriever;
import bg.sofia.uni.fmi.mjt.cookingcompass.request.RequestMaker;
import bg.sofia.uni.fmi.mjt.cookingcompass.request.RequestSender;

import java.util.List;

public class CookingCompass implements CookingCompassAPI {

    @Override
    public List<Recipe> getRecipes(List<String> keywords, List<String> mealTypes, List<String> healthTags)
        throws IncorrectHealthException, IncorrectMealTypeException {

        if (!checkHealthTags(healthTags)) {
            throw new IncorrectHealthException("Invalid health tags.");
        }

        if (!checkMealTypes(mealTypes)) {
            throw new IncorrectMealTypeException("Invalid meal types.");
        }

        RequestMaker requestMaker = new RequestMaker(keywords, mealTypes, healthTags, new RequestSender());
        RecipeRetriever recipeRetriever = new RecipeRetriever(requestMaker);

        return recipeRetriever.retrieve();
    }

    private boolean checkMealTypes(List<String> mealTypes) {
        if (mealTypes != null) {
            for (String mealType: mealTypes) {
                if (!MEAL_TYPES.contains(mealType)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkHealthTags(List<String> healthTags) {
        if (healthTags != null) {
            for (String healthTag: healthTags) {
                if (!HEALTH_TAGS.contains(healthTag)) {
                    return false;
                }
            }
        }
        return true;
    }
}
