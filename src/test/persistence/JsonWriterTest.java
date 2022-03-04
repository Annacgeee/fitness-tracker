package persistence;

import model.DailyConsumption;
import model.FoodItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            DailyConsumption wr = new DailyConsumption("Anna's Daily consumption", 500);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyDailyConsumption() {
        try {
            DailyConsumption dc = new DailyConsumption("Anna's Daily consumption",500);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(dc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            dc = reader.read();
            assertEquals("Anna's Daily consumption", dc.getName());
            assertEquals(0, dc.numFoodItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDailyConsumption() {
        try {
            DailyConsumption dc = new DailyConsumption("Anna's Daily consumption",500);
            dc.addFoodItem(new FoodItem("egg",100));
            dc.addFoodItem(new FoodItem("oats", 100));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(dc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            dc = reader.read();
            assertEquals("Anna's Daily consumption", dc.getName());
            List<FoodItem> foodItem = dc.getFoodItem();
            assertEquals(2, foodItem.size());
            checkFoodItem("egg", 100, foodItem.get(0) );
            checkFoodItem("oats", 100,foodItem.get(1) );

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
