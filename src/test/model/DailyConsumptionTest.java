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
        this.tuesdayConsumption = new DailyConsumption(1000);
        this.wednesdayConsumption = new DailyConsumption(0);
        oatmeal = new FoodItem("oatmeal", 30);
        chocolate = new FoodItem("chocolate", 120);
    }

    @Test
    public void testConstructor() {
        assertEquals(500, mondayConsumption.getRemainingCalories());
        assertEquals(1000, tuesdayConsumption.getRemainingCalories());
        assertTrue(foodItems.isEmpty());
    }

    @Test
    public void testAddFoodItem() {
        assertTrue(wednesdayConsumption.getFoodItems().isEmpty());
        wednesdayConsumption.addFoodItem(chocolate);
        assertEquals(0, wednesdayConsumption.getRemainingCalories());
        assertEquals(1, wednesdayConsumption.getFoodItems().size());

        assertTrue(mondayConsumption.getFoodItems().isEmpty());
        mondayConsumption.addFoodItem(oatmeal);
        assertEquals(470, mondayConsumption.getRemainingCalories());
        assertEquals(1, mondayConsumption.getFoodItems().size());
        //assertEquals(oatmeal,consumption1.getFoodItems());

        mondayConsumption.addFoodItem(chocolate);
        assertEquals(350, mondayConsumption.getRemainingCalories());
        assertEquals(2, mondayConsumption.getFoodItems().size());
        //assertEquals((chocolate, oatmeal), consumption1.getFoodItems());
    }

}
