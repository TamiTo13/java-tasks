import bg.sofia.uni.fmi.mjt.cookingcompass.CookingCompass;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CookingCompass compass = new CookingCompass();
        try {
            System.out.println(compass.getRecipes(List.of("chicken", "tomato", "cheese"),
                    List.of("Breakfast"),
                    List.of("vegan")));
        } catch (Exception e) {
            System.out.println("Main check");
        }

    }
}
