package bg.sofia.uni.fmi.mjt.cookingcompass.recipe;

public record Recipe(String label, String dietLabels, String healthLabels, String totalWeight,
                     String cuisineType, String mealType, String dishType, String IngredientLines) {
}
