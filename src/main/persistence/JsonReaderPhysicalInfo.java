package persistence;

import model.PhysicalInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderPhysicalInfo {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderPhysicalInfo(String source) {
        this.source = source;
    }

    // EFFECTS: reads PhysicalInfo from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PhysicalInfo read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePhysicalInfo(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses physical info from JSON object and returns it
    private PhysicalInfo parsePhysicalInfo(JSONObject jsonObject) {
        int weight = jsonObject.getInt("weight");
        int height = jsonObject.getInt("height");
        int age = jsonObject.getInt("age");
        Boolean gender = jsonObject.getBoolean("gender");
        double caloriesNeeded = jsonObject.getDouble("caloriesNeeded");

        PhysicalInfo pi = new PhysicalInfo(weight,height,age,gender, caloriesNeeded);

        return pi;
    }



}
