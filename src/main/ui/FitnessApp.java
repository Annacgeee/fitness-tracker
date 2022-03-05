package ui;

import model.PhysicalInfo;
import model.DailyConsumption;
import model.FoodItem;
import persistence.JsonReader;
import persistence.JsonReaderPhysicalInfo;
import persistence.JsonWriter;
import persistence.JsonWriterPhysicalInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class FitnessApp {
    private static final String JSON_STORE = "./data/dailyConsumption.json";
    private static final String JSON_STORE2 = "./data/physicalInfo.json";
    private PhysicalInfo physicalInfo;
    private DailyConsumption dailyConsumption;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonWriterPhysicalInfo jsonWriter2;
    private JsonReaderPhysicalInfo jsonReader2;
    private JsonReader jsonReader;

    //EFFECTS: construct a daily consumption and runs the application
    public FitnessApp() throws FileNotFoundException {

        input = new Scanner(System.in);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriterPhysicalInfo(JSON_STORE2);
        jsonReader2 = new JsonReaderPhysicalInfo(JSON_STORE2);

        runFitnessApp();

    }

    //MODIFIES: this
    //Effects: send out command to guide user setup their physical info
    private void runFitnessApp() {
        menu();
    }


    private void menu() {
        String load = "";
        while (!load.equals("Y") && !load.equals("N")) {
            System.out.println("Would you like to load your profile? (Y/N)");
            load = input.nextLine();
            if (load.equals("Y")) {
                loadPhysicalInfo();
                loadDailyConsumption();
            }
        }
        int option = -1;
        while (option != 0) {
            displayMenu();
            option = input.nextInt();
            // split menu code for no actual reason because function line limit I guess
            if (0 <= option && option <= 3) {
                parseMenuOptionPart1(option);
            } else if (4 <= option && option <= 6) {
                parseMenuOptionPart2(option);
            } else {
                System.out.println("Please select a valid option!\n");
            }
        }
    }

    //REQUIRES: user has valid inputs
    //MODIFIES:PhysicalInfo
    // EFFECTS:Initialize physical info based on user input
    private PhysicalInfo initializePhysicalInfo() {
        System.out.println("Please enter your weight in kg");
        double myWeight = input.nextDouble();
        System.out.println("please enter your height in cm");
        int myHeight = input.nextInt();
        System.out.println("please enter your age");
        int myAge = input.nextInt();
        String myGenderStr = "";
        input.nextLine();
        while (!myGenderStr.equals("M") && !myGenderStr.equals("F")) {
            System.out.println("please enter your gender (M/F)");
            myGenderStr = input.nextLine();
        }
        boolean myGender = false;
        if (myGenderStr.equals("M")) {
            myGender = false;
        } else if (myGenderStr.equals("F")) {
            myGender = true;
        }
        return new PhysicalInfo(myWeight, myHeight, myAge, myGender);
    }

    //MODIFIES: PhysicalInfo
    //EFFECTS: calculate calories needed according to user input
    private void calculateCalories() {
        System.out.print("what is your goal?");
        displayGoals();
        int myGoal = input.nextInt();
        if (myGoal == 1) {
            System.out.print("what is your weekly goal?");
            displayOptions();
            int mySelect = input.nextInt();
            processWeeklyGoalInput(mySelect);
        } else {
            physicalInfo.calculateCaloriesNeededForMaintainWeight();
            System.out.println("Your daily maximum calories is " + physicalInfo.getCaloriesNeeded());

        }
    }




    //EFFECTS:display menu for user to choose
    private void displayMenu() {
        System.out.println("\nSelect by pressing number of option:");
        System.out.println("1) Log my personal physical information");
        System.out.println("2) Print my personal physical information");
        System.out.println("3) Show calories left");
        System.out.println("4) Add a food item");
        System.out.println("5) Print food item list");
        System.out.println("6) Save my profile");
        System.out.println("0) Quit");

    }

    //EFFECTS: display goal option for user to choose
    private void displayGoals() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> I want to lose weight");
        System.out.println("\t2 -> I want to maintain weight");
    }


    //EFFECTS: displays weekly fitness goal options for user to choose
    private void displayOptions() {
        double first = 0.2;
        double second = 0.5;
        double third = 0.8;
        int fourth = 1;

        System.out.println("\nSelect from:");
        System.out.println("\t1 -> lose " + first + "kg per week");
        System.out.println("\t2 -> lose " + second + "kg per week");
        System.out.println("\t3 -> lose " + third + "kg per week");
        System.out.println("\t4 -> lose " + fourth + "kg per week");
    }


    //MODIFIES: PhysicalInfo
    //EFFECTS: process user's weekly lose weight goal
    public void processWeeklyGoalInput(int mySelect) {
        physicalInfo.calculateCaloriesToLoseWeight(mySelect);
        System.out.println("Your daily maximum calories is " + physicalInfo.getCaloriesNeeded());
    }

    //MODIFIES: THIS
    //EFFECTS: add a food item to daily consumption
    private void addFoodItem() {
        input.nextLine();
        System.out.println("What food would you like to add?");
        String foodName = input.nextLine();
        dailyConsumption.addFoodItem(new FoodItem(foodName, 100));//set all food calories default is 100
        //
    }

    //MODIFIES: this
    // EFFECTS: prints all the food items in daily consumption to console
    private void printFoodItems() {
        if (dailyConsumption == null) {
            System.out.println("No food items available");
            return;
        }
        List<FoodItem> foodItemList = dailyConsumption.getFoodItem();
        for (FoodItem f : foodItemList) {
            System.out.println(f.toString());
        }
    }

    //EFFECTS: save the food list to file
    public void saveDailyConsumption() {
        try {
            jsonWriter.open();
            jsonWriter.write(dailyConsumption);
            jsonWriter.close();
            System.out.println("Saved " + dailyConsumption.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads daily consumption from file
    private void loadDailyConsumption() {
        try {
            dailyConsumption = jsonReader.read();
            System.out.println("Loaded " + dailyConsumption.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: save personal physical info
    private void savePhysicalInfo() {
        try {
            jsonWriter2.open();
            jsonWriter2.write(physicalInfo);
            jsonWriter2.close();
            System.out.println("Physical information saved " + " to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE2);
        }

    }

    //EFFECTS: load physical info
    private void loadPhysicalInfo() {
        try {
            physicalInfo = jsonReader2.read();
            System.out.println("Physical information Loaded " + " from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE2);
        }
    }

    // EFFECTS: prints all physical info to console
    private void printPhysicalInfo() {
        if (physicalInfo == null) {
            System.out.println("No physical info available");
        } else {
            System.out.println(physicalInfo.toString());
        }
    }


    // EFFECTS: parse menu option 0~3
    private void parseMenuOptionPart1(int option) {
        switch (option) {
            case 1:
                this.physicalInfo = initializePhysicalInfo();
                calculateCalories();
                dailyConsumption = new DailyConsumption(
                        "Anna's daily consumption",
                        physicalInfo.getCaloriesNeeded()
                );
                break;
            case 2:
                printPhysicalInfo();
                break;
            case 3:
                if (dailyConsumption == null) {
                    System.out.println("Please initialize profile first!");
                } else {
                    System.out.println("You have " + dailyConsumption.getRemainingCalories() + " remaining calories");
                }
                break;
            default:
                break;
        }
    }


    // EFFECTS: parse menu option 4~6
    private void parseMenuOptionPart2(int option) {
        switch (option) {
            case 4:
                addFoodItem();
                break;
            case 5:
                printFoodItems();
                break;
            case 6:
                if (physicalInfo == null || dailyConsumption == null) {
                    System.out.println("Please initialize profile first!");
                    break;
                }
                savePhysicalInfo();
                saveDailyConsumption();
                break;
            default:
                break;
        }
    }

}



