package persistence;

import model.FoodItem;
import model.PhysicalInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFoodItem(String name, int calories, FoodItem foodItem) {
        assertEquals(name,foodItem.getFoodName());
        assertEquals(calories,foodItem.getCalories());
       // assertEquals()

    }

    protected void checkPhysicalInfo(int weight, int height, int age, boolean gender, PhysicalInfo physicalInfo) {
        assertEquals(weight,physicalInfo.getWeight());
        assertEquals(height,physicalInfo.getHeight());
        assertEquals(age,physicalInfo.getAge());
        assertEquals(gender,physicalInfo.getGender());


    }
}
