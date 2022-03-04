package ui;

import model.PhysicalInfo;
import model.DailyConsumption;
import model.FoodItem;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class FitnessApp {
    private static final String JSON_STORE = "./data/dailyConsumption.json";
    private PhysicalInfo physicalInfo;
    private DailyConsumption dailyConsumption;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: construct a daily consumption and runs the application
    public FitnessApp() throws FileNotFoundException {

        input = new Scanner(System.in);
        //dailyConsumption = new DailyConsumption("Anna's daily consumption", 200);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFitnessApp();

    }

    //MODIFIES: this
    //Effects: send out command to guide user setup their physical info
    private void runFitnessApp() {
        this.physicalInfo = initializePhysicalInfo();
        calculateCalories();
        dailyConsumption = new DailyConsumption("Anna's daily consumption", physicalInfo.getCaloriesNeeded());
        menu();
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


    private void menu() {
        boolean keepGoing = true;

        //input.nextLine();
        while (keepGoing) {
            displayMenu();
            String option = "";

            option = input.nextLine();
            if (option.equals("Q")) {
                keepGoing = false;
            } else if (option.equals("C")) {
                System.out.println("You have " + dailyConsumption.getRemainingCalories() + " remaining calories");
            } else if (option.equals("F")) {
                addFoodItem();
            } else if (option.equals("P")) {
                printFoodItems();
            } else if (option.equals("S")) {
                saveDailyConsumption();
            } else if (option.equals("L")) {
                loadDailyConsumption();
            }  else {
                System.out.println("Please enter valid option!");
            }
        }
    }

    //EFFECTS:display menu for user to choose
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("Show calories left (C)");
        System.out.println("Add a food item (F)");
        System.out.println("Print food item list (P)");
        System.out.println("Save food item list to file(S)");
        System.out.println("load food item list from file(L)");
        System.out.println("Quit (Q)");

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
        System.out.println("What food would you like to add?");
        String foodName = input.nextLine();
        dailyConsumption.addFoodItem(new FoodItem(foodName, 100));//set all food calories default is 100
        //
    }

    //MODIFIES: this
    // EFFECTS: prints all the food items in daily consumption to console
    private void printFoodItems() {
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
}



