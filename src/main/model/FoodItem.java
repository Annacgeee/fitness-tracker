package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class FoodItem implements Writable {
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

    @Override
    // Effects: create a JSON object
    //cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",foodName);
        json.put("calories",calories);
        return json;
    }

    //EFFECT: return to string
    public String toString() {
        return foodName + " : " + calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FoodItem foodItem = (FoodItem) o;
        return calories == foodItem.calories && Objects.equals(foodName, foodItem.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, calories);
    }
}
