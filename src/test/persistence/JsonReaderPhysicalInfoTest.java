package persistence;

import model.PhysicalInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderPhysicalInfoTest  {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderPhysicalInfo reader = new JsonReaderPhysicalInfo("./data/noSuchFile.json");
        try {
            PhysicalInfo wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderPhysicalInfo() {
        try {
            PhysicalInfo pi = new PhysicalInfo(100,170,22,true);
            JsonWriterPhysicalInfo writer = new JsonWriterPhysicalInfo("./data/testReaderGeneralPhysicalInfo.json");
            writer.open();
            writer.write(pi);
            writer.close();

            JsonReaderPhysicalInfo reader = new JsonReaderPhysicalInfo("./data/testReaderGeneralPhysicalInfo.json");
            pi = reader.read();
            assertEquals(100, pi.getWeight());
            assertEquals(170, pi.getHeight());
            assertEquals(22,pi.getAge());
            assertTrue(pi.getGender());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
