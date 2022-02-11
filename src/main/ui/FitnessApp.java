package ui;

import model.PhysicalInfo;

import java.util.Scanner;


public class FitnessApp {
    private PhysicalInfo physicalInfo;
    private Scanner input;

    //EFFECTS: runs the application
    public FitnessApp() {

        runFitnessApp();
    }

    //MODIFIES: Physical Info class
    //Effects: send out command to guide user setup their physical info
    @SuppressWarnings("checkstyle:MethodLength")
    private void runFitnessApp() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter your weight in kg");
        double myWeight = scan.nextDouble();
        System.out.println("please enter your height in cm");
        int myHeight = scan.nextInt();
        System.out.println("please enter your age");
        int myAge = scan.nextInt();
        String myGenderStr = "";
        scan.nextLine(); // consume new line character
        while (!myGenderStr.equals("M") && !myGenderStr.equals("F")) {
            System.out.println("please enter your gender (M/F)");
            myGenderStr = scan.nextLine();
        }
        boolean myGender = false;
        if (myGenderStr == "male") {
            myGender = false;
        } else if (myGenderStr == "female") {
            myGender = true;
        }

        physicalInfo = new PhysicalInfo(myWeight, myHeight, myAge, myGender);

        System.out.print("what is your goal?");
        displayGoals();
        int myGoal = scan.nextInt();
        if (myGoal == 1) {
            System.out.print("what is your weekly goal?");
            displayOptions();
            int mySelect = scan.nextInt();
            processWeeklyGoalInput(mySelect);
        } else {
            physicalInfo.calculateCaloriesNeededForMaintainWeight();
            System.out.println("Your daily maximum calories is " + physicalInfo.getCaloriesNeeded());
        }

    }

    //EFFECTS: display goal option
    private void displayGoals() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> I want to lose weight");
        System.out.println("\t2 -> I want to maintain weight");
    }


    //EFFECTS: displays weekly fitness goal options
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



