package persistence;

import model.DailyConsumption;
import model.FoodItem;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DailyConsumption dc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDailyConsumption() {

        try {
            DailyConsumption dc = new DailyConsumption("My Daily consumption",500);
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyWorkroom.json");
            writer.open();
            writer.write(dc);
            writer.close();
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        }

        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            DailyConsumption dc = reader.read();
            assertEquals("My Daily consumption", dc.getName());
            assertEquals(0, dc.numFoodItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDailyConsumption() {

        try {
            DailyConsumption dc = new DailyConsumption("My Daily consumption", 500);
            dc.addFoodItem(new FoodItem("egg", 100));
            dc.addFoodItem(new FoodItem("oats", 100));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralWorkroom.json");
            writer.open();
            writer.write(dc);
            writer.close();
        } catch (java.io.FileNotFoundException e) {
            fail("Writer could not open file");
        }

        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            DailyConsumption dc = reader.read();
            assertEquals("My Daily consumption", dc.getName());
            List<FoodItem> foodItems = dc.getFoodItem();
            assertEquals(2, foodItems.size());
            checkFoodItem("egg", 100, foodItems.get(0));
            checkFoodItem("oats", 100, foodItems.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
