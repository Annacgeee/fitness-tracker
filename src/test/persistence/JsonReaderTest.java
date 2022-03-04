package persistence;

import model.DailyConsumption;
import model.FoodItem;
import org.junit.jupiter.api.Test;

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
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            DailyConsumption dc = reader.read();
            assertEquals("Anna's Daily consumption", dc.getName());
            assertEquals(0, dc.numFoodItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderGeneralDailyConsumption() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            DailyConsumption dc = reader.read();
            assertEquals("Anna's Daily consumption", dc.getName());
            List<FoodItem> thingies = dc.getFoodItem();
            assertEquals(2, thingies.size());
            //checkThingy("needle", Category.STITCHING, thingies.get(0));
            //checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
