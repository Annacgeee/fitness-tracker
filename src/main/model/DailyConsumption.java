package model;

import java.util.ArrayList;
// methods in this class
// addFoodItem: able to add a food item to foodlist
// removeFoodItem: able to delete a food item from foodlist

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
    // should be reduced by the calories of the food, if the remaining calories is already zero,then it won't change
    public void addFoodItem(FoodItem foodItem) {
        this.foodItems.add(foodItem);
        if (remainingCalories == 0) {
            this.remainingCalories = 0;
        } else {
            this.remainingCalories = remainingCalories - foodItem.getCalories();
        }
    }

    //REQUIRES:FoodItem is in the food list
    //MODIFIES: this
    //EFFECTS: when a food is removed, the food item should be removed from food list, also the food calories should be
    //           added back to the remaining calories

    public void removeFoodItem(){
    //write this in second phase
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
