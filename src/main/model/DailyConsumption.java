package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// daily consumption class to keep list of foods added and remaining calories for user, and consumption name
public class DailyConsumption implements Writable {
    private List<FoodItem> foodItems;
    private double remainingCalories;
    private String name;

    //EFFECT:construct daily consumption contains the remaining calories for the day, and also the list of food that
    //       have been added, name of the consumption

    public DailyConsumption(String name, double remainingCalories) {
        this.remainingCalories = remainingCalories;
        this.foodItems = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }


    //MODIFIES: this
    //EFFECT: when  food is added, the food item list should be updated with the food, also the remaining calories
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
        EventLog.getInstance().logEvent(new Event(foodItem.getFoodName()
                + " is added to today's food intake list"));
    }

    //EFFECTS: returns an unmodifiable list of food items in this daily consumption
    //cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public List<FoodItem> getFoodItem() {
        return Collections.unmodifiableList(foodItems);
    }

    //EFFECTS: returns number of food items in this daily consumption
    //cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public int numFoodItems() {
        return foodItems.size();
    }


    //EFFECTS:return the current remaining calories
    public double getRemainingCalories() {
        return this.remainingCalories;
    }

    public void removeFoodItem(String foodItem) {
        for (FoodItem next : foodItems) {
            if (next.getFoodName() == foodItem) {
                foodItems.remove(next);
            }
        }

        EventLog.getInstance().logEvent(new Event(foodItem + " is removed from the list"));

    }

    public void saveFoodList() {
        EventLog.getInstance().logEvent(new Event("Your food items have been saved to today's food list"));
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
