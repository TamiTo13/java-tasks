package bg.sofia.uni.fmi.mjt.cookingcompass;

import bg.sofia.uni.fmi.mjt.cookingcompass.exception.IncorrectHealthException;
import bg.sofia.uni.fmi.mjt.cookingcompass.exception.IncorrectMealTypeException;
import bg.sofia.uni.fmi.mjt.cookingcompass.recipe.Recipe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface CookingCompassAPI {
    static final Set<String> MEAL_TYPES = new HashSet<>(List.of("Breakfast", "Dinner", "Lunch", "Snack", "Teatime"));

    static final Set<String> HEALTH_TAGS = new HashSet<>(List.of(
            "alcohol-cocktail", "alcohol-free", "celery-free", "crustacean-free", "dairy-free",
            "DASH", "egg-free", "fish-free", "fodmap-free", "gluten-free", "immuno-supportive", "keto-friendly",
            "kidney-friendly", "kosher", "low-fat-abs", "low-potassium", "low-sugar", "lupine-free", "Mediterranean",
            "mollusk-free", "mustard-free", "no-oil-added", "paleo", "peanut-free", "pescatarian", "pork-free",
            "red-meat-free", "sesame-free", "shellfish-free", "soy-free", "sugar-conscious",
            "sulfite-free", "tree-nut-free", "vegan", "vegetarian", "wheat-free"));

    List<Recipe> getRecipes(List<String> keywords, List<String> mealType, List<String> dietType)
            throws IncorrectHealthException, IncorrectMealTypeException;

}
