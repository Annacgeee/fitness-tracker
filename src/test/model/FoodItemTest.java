package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodItemTest {
    private FoodItem testFoodItem;

    @BeforeEach
    void runBefore() {
        testFoodItem = new FoodItem("oatmeal", 30);
    }

    @Test
    void testConstructor() {
        assertEquals("oatmeal", testFoodItem.getFoodName());
        assertEquals(30, testFoodItem.getCalories());
    }

    @Test
    void toStringTest() {
        assertEquals("oatmeal : 30",testFoodItem.toString());
    }
}