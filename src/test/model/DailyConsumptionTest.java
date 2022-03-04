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
        this.mondayConsumption = new DailyConsumption( "Anna",500);
        this.tuesdayConsumption = new DailyConsumption("Max",20);
        this.wednesdayConsumption = new DailyConsumption("Carol", 10);
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
        assertTrue(wednesdayConsumption.getFoodItem().isEmpty());
        wednesdayConsumption.addFoodItem(chocolate);
        assertEquals(0, wednesdayConsumption.getRemainingCalories());
        foodItems.add(chocolate);
        assertEquals(foodItems, wednesdayConsumption.getFoodItem());

        tuesdayConsumption.addFoodItem(chocolate);
        assertEquals(0, tuesdayConsumption.getRemainingCalories());
    }

    @Test
    public void testAddFoodItemForRemainingIsNotZero() {

        assertTrue(mondayConsumption.getFoodItem().isEmpty());
        mondayConsumption.addFoodItem(oatmeal);
        foodItems.add(oatmeal);
        assertEquals(470, mondayConsumption.getRemainingCalories());

        assertEquals(foodItems, mondayConsumption.getFoodItem());

        mondayConsumption.addFoodItem(chocolate);
        foodItems.add(chocolate);
        assertEquals(350, mondayConsumption.getRemainingCalories());
        assertEquals(2, mondayConsumption.getFoodItem().size());
        assertEquals(foodItems, mondayConsumption.getFoodItem());
    }

    @Test
    public void testNumFoodItemZero() {
        assertEquals(0,foodItems.size());
    }

    @Test
    public void testGeneralFoodItemZero(){
        foodItems.add(oatmeal);
        foodItems.add(chocolate);
        assertEquals(2,foodItems.size());

    }

    @Test
    public void testGetFoodItem(){
        foodItems.add(oatmeal);
        foodItems.add(chocolate);
        assertEquals(2,foodItems.size());
        assertEquals(oatmeal,foodItems.get(0));
        assertEquals(chocolate,foodItems.get(1));
    }
}
