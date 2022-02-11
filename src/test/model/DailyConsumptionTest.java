package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DailyConsumptionTest {
    private DailyConsumption mondayConsumption;
    private DailyConsumption tuesdayConsumption;
    private DailyConsumption wednesdayConsumption;
    private ArrayList<FoodItem> foodItems;
    private FoodItem oatmeal;
    private FoodItem chocolate;


    @BeforeEach
    public void setup() {
        this.mondayConsumption = new DailyConsumption(500);
        this.tuesdayConsumption = new DailyConsumption(20);
        this.wednesdayConsumption = new DailyConsumption(0);
        oatmeal = new FoodItem("oatmeal", 30);
        chocolate = new FoodItem("chocolate", 120);
        foodItems = new ArrayList<>();
    }

    @Test
    public void testConstructor() {
        assertEquals(500, mondayConsumption.getRemainingCalories());
        assertEquals(20, tuesdayConsumption.getRemainingCalories());
        assertTrue(foodItems.isEmpty());
    }

    @Test
    public void testAddFoodItemForRemainingIsZero() {
        assertTrue(wednesdayConsumption.getFoodItems().isEmpty());
        wednesdayConsumption.addFoodItem(chocolate);
        assertEquals(0, wednesdayConsumption.getRemainingCalories());
        foodItems.add(chocolate);
        assertEquals(foodItems, wednesdayConsumption.getFoodItems());

        tuesdayConsumption.addFoodItem(chocolate);
        assertEquals(0, tuesdayConsumption.getRemainingCalories());
    }

    @Test
    public void testAddFoodItemForRemainingIsNotZero() {

        assertTrue(mondayConsumption.getFoodItems().isEmpty());
        mondayConsumption.addFoodItem(oatmeal);
        foodItems.add(oatmeal);
        assertEquals(470, mondayConsumption.getRemainingCalories());

        assertEquals(foodItems, mondayConsumption.getFoodItems());

        mondayConsumption.addFoodItem(chocolate);
        foodItems.add(chocolate);
        assertEquals(350, mondayConsumption.getRemainingCalories());
        assertEquals(2, mondayConsumption.getFoodItems().size());
        assertEquals(foodItems, mondayConsumption.getFoodItems());
    }
}
