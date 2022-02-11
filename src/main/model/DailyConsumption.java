package model;

import java.util.ArrayList;


public class DailyConsumption {
    private PhysicalInfo physicalInfo;
    private ArrayList<FoodItem> foodItems;
    private double remainingCalories;

    //EFFECT:construct daily consumption contains the remaining calories for the day, and also the list of food that
    //       have been added
    public  DailyConsumption(double remainingCalories) {
        this.remainingCalories = remainingCalories;
        this.foodItems = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECT: when a food is added, the food item list should be updated with the food, also the remaining calories
    // should be reduced by the calories of the food, if the remaining calories is already zero,then it won't change.
    // if there is not enough remaining calories to add food, the remaining calories will be zero after add the food.
    public void addFoodItem(FoodItem foodItem) {
        this.foodItems.add(foodItem);
        if (remainingCalories <= 0) {
            this.remainingCalories = 0;
        } else if (foodItem.getCalories() > remainingCalories) {
            this.remainingCalories = 0;
        } else {
            this.remainingCalories = remainingCalories - foodItem.getCalories();
        }
    }


    //EFFECTS:return the current remaining calories
    public double getRemainingCalories() {
        return this.remainingCalories;
    }

    //EFFECTS: return the current food item list
    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

}
