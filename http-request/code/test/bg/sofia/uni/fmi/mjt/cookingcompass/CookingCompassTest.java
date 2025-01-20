package bg.sofia.uni.fmi.mjt.cookingcompass;

import bg.sofia.uni.fmi.mjt.cookingcompass.exception.IncorrectHealthException;
import bg.sofia.uni.fmi.mjt.cookingcompass.exception.IncorrectMealTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CookingCompassTest {

    @Test
    void testIncorrectHealthTags() {
        CookingCompass compass = new CookingCompass();

        Assertions.assertThrows(IncorrectHealthException.class,
                () -> compass.getRecipes(List.of("chicken"),List.of("Breakfast"),
                        List.of("wrong")));

        Assertions.assertThrows(IncorrectHealthException.class,
                () -> compass.getRecipes(List.of("chicken"),List.of("Breakfast"),
                        List.of("vegan", "vegetarian", "dummy")));

    }

    @Test
    void testIncorrectMealTypes() {
        CookingCompass compass = new CookingCompass();

        Assertions.assertThrows(IncorrectMealTypeException.class,
                () -> compass.getRecipes(List.of("chicken"),List.of("break-fast"), List.of("vegan")));

        Assertions.assertThrows(IncorrectMealTypeException.class,
                () -> compass.getRecipes(List.of("chicken"),List.of("Breakfast", "Supper"), List.of("vegan")));
    }
}
