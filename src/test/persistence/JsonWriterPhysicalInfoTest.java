package persistence;

import model.PhysicalInfo;
import org.junit.jupiter.api.Test;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterPhysicalInfoTest   {

    @Test
    void testWriterInvalidFile() {
        try {
            PhysicalInfo pi = new PhysicalInfo(100,170,22,true);
            JsonWriterPhysicalInfo writer = new JsonWriterPhysicalInfo("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPhysicalInfo() {
        try {
            PhysicalInfo pi = new PhysicalInfo(100,170,22,true);
            JsonWriterPhysicalInfo writer = new JsonWriterPhysicalInfo("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(pi);
            writer.close();

            JsonReaderPhysicalInfo reader = new JsonReaderPhysicalInfo("./data/testWriterEmptyWorkroom.json");
            pi = reader.read();
            assertEquals(100, pi.getWeight());
            assertEquals(170, pi.getHeight());
            assertEquals(22,pi.getAge());
            assertTrue(pi.getGender());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
