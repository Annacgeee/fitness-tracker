package persistence;


import model.PhysicalInfo;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//// Represents a writer that writes JSON representation of workroom to file
// cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterPhysicalInfo {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    // cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JsonWriterPhysicalInfo(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of physical info to file
    // cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void write(PhysicalInfo pi) {
        JSONObject json = pi.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // cite from demo,https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveToFile(String json) {
        writer.print(json);
    }
}
