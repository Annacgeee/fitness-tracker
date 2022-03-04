package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DailyConsumption implements Writable {
    private List<FoodItem> foodItems;
    private double remainingCalories;
    private String name;

    //EFFECT:construct daily consumption contains the remaining calories for the day, and also the list of food that
    //       have been added


    ///////////
    //daily consumption being modied, remaining calroies removed from param
    public  DailyConsumption(String name, double remainingCalories) {
        this.remainingCalories = remainingCalories;
        this.foodItems = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
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

    //EFFECTS: returns an unmodifiable list of fooditems in this daily consumption
    //cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public List<FoodItem> getFoodItem() {
        return Collections.unmodifiableList(foodItems);
    }

    //EFFECTS: returns number of fooditems in this daily consumption
    //cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public int numFoodItems() {
        return foodItems.size();
    }


    //EFFECTS:return the current remaining calories
    public double getRemainingCalories() {
        return this.remainingCalories;
    }


    @Override
    //EFFECTS: create json object of daily consumption
    //cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("foodItems", foodItemsToJson());
        json.put("remainingCalories", remainingCalories);
        return json;
    }

    //EFFECTS: returns food items in this daily consumption as a json array
    //cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private JSONArray foodItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FoodItem t : foodItems) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;

    }
}
