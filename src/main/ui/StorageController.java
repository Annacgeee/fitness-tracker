package ui;

import model.PhysicalInfo;
import model.DailyConsumption;
import model.FoodItem;
import persistence.JsonReader;
import persistence.JsonReaderPhysicalInfo;
import persistence.JsonWriter;
import persistence.JsonWriterPhysicalInfo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class StorageController {
    private static final String JSON_STORE = "./data/dailyConsumption.json";

    private JsonWriter jsonWriter;

    private JsonReader jsonReader;

    private JsonWriterPhysicalInfo physicalInfoWriter;
    private JsonReaderPhysicalInfo physicalInfoReader;
    private static final String physicalInfoJSON = "./data/physicalInfo.json";


    public StorageController() throws FileNotFoundException {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        physicalInfoWriter = new JsonWriterPhysicalInfo(physicalInfoJSON);
        physicalInfoReader = new JsonReaderPhysicalInfo(physicalInfoJSON);
    }


    //EFFECTS: save personal physical info
    public void savePhysicalInfo(PhysicalInfo physicalInfo) {
        try {
            physicalInfoWriter.open();
            physicalInfoWriter.write(physicalInfo);
            physicalInfoWriter.close();
            System.out.println("Physical information saved " + " to " + physicalInfoJSON);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + physicalInfoJSON);
        }

    }

    //EFFECTS: load physical info
    public PhysicalInfo loadPhysicalInfo() {
        PhysicalInfo physicalInfo = null;
        try {
            physicalInfo = physicalInfoReader.read();
            System.out.println("Physical information Loaded " + " from " + physicalInfoJSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + physicalInfoJSON);
        }
        return physicalInfo;
    }




/*
        //MODIFIES: this
        // EFFECTS: prints all the food items in daily consumption to console
        private void printFoodItems () {
            if (dailyConsumption == null) {
                System.out.println("No food items available");
                return;
            }
            List<FoodItem> foodItemList = dailyConsumption.getFoodItem();
            for (FoodItem f : foodItemList) {
                System.out.println(f.toString());
            }
        }

 */

    //EFFECTS: save the food list to file
    public void saveDailyConsumption(DailyConsumption dailyConsumption) {
        try {
            jsonWriter.open();
            jsonWriter.write(dailyConsumption);
            jsonWriter.close();
            System.out.println("Saved " + dailyConsumption.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

        /*


        //MODIFIES: this
        //EFFECTS: loads daily consumption from file
        private void loadDailyConsumption () {
            try {
                dailyConsumption = jsonReader.read();
                System.out.println("Loaded " + dailyConsumption.getName() + " from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }


        // EFFECTS: prints all physical info to console
        private void printPhysicalInfo () {
            if (physicalInfo == null) {
                System.out.println("No physical info available");
            } else {
                System.out.println(physicalInfo.toString());
            }
        }


      


         */




}



