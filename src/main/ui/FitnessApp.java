package ui;

import model.PhysicalInfo;
import model.DailyConsumption;
import model.FoodItem;

import java.util.Scanner;


public class FitnessApp {
    private PhysicalInfo physicalInfo;
    private DailyConsumption dailyConsumption;
    private Scanner input;

    //EFFECTS: runs the application
    public FitnessApp() {

        input = new Scanner(System.in);
        runFitnessApp();
    }

    //MODIFIES: PhysicalInfo,DailyConsumption
    //Effects: send out command to guide user setup their physical info
    private void runFitnessApp() {
        this.physicalInfo = initializePhysicalInfo();
        calculateCalories();
        dailyConsumption = new DailyConsumption(dailyConsumption.getName());
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
        input.nextLine();
        while (true) {
            String option = "";
            System.out.println("\nWhat would you like to do?");
            System.out.println("Show calories left (C)");
            System.out.println("Add a food item (F)");
            System.out.println("Quit (Q)");
            option = input.nextLine();
            if (option.equals("C")) {
                System.out.println("You have " + dailyConsumption.getRemainingCalories() + " remaining calories");
            } else if (option.equals("F")) {
                System.out.println("What food would you like to add?");
                String foodName = input.nextLine();
                dailyConsumption.addFoodItem(new FoodItem(foodName, 100));//set all food calories default is 100
                //                                                            for now, I will change it in later phase
            } else if (option.equals("Q")) {
                break;
            } else {
                System.out.println("Please enter valid option!");
            }
        }
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
}



