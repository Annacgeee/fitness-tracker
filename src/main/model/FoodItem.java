package model;

public class FoodItem {
    private String foodName;
    private int calories;

    //REQUIRES: foodName has a non-zero length
    //MODIFIES: this
    //EFFECT: construct food name and how many calories does it contain in 100g
    public FoodItem(String name, int calories) {
        this.foodName = name;
        this.calories = calories;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public int getCalories() {
        return this.calories;
    }

}