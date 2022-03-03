package persistence;


import model.DailyConsumption;
import model.FoodItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads daily consumption from JSON data stored in file
//cite from demo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads daily consumption from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DailyConsumption read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDailyConsumption(jsonObject);

    }

    //EFFECTS:reads source file as string and returns it
    private String readFile(String string) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    //EFFECTS: parses daily consumption from JSON object and return it

    private DailyConsumption parseDailyConsumption(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        DailyConsumption dc = new DailyConsumption(name);
        addFoodItems(dc,jsonObject);
        return dc;

    }

    // MODIFIES: daily consumption
    // EFFECTS: parses fooditems from JSON object and adds them to daily consumption
    private void addFoodItems(DailyConsumption dc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodItems");
        for (Object json : jsonArray) {
            JSONObject nextFoodItem = (JSONObject) json;
            addFoodItem(dc, nextFoodItem);
        }
    }

    //MODIFIES: dc
    // EFFECTS: parses fooditem from json object and add it to workroom
    private void addFoodItem(DailyConsumption dc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int calories = jsonObject.getInt("calories");
        /// should do something to calories???

        FoodItem foodItem = new FoodItem(name,calories);
        dc.addFoodItem(foodItem);
    }

}
