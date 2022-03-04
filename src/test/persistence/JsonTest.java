package persistence;

import model.FoodItem;
import model.PhysicalInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFoodItem(String name, int calories, FoodItem foodItem) {
        assertEquals(name,foodItem.getFoodName());
        assertEquals(calories,foodItem.getCalories());


    }


}
