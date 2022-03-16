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


public class FitnessAppGUI extends JPanel
        implements ListSelectionListener {
    private static final String JSON_STORE = "./data/dailyConsumption.json";
    private static final String JSON_STORE2 = "./data/physicalInfo.json";
    private PhysicalInfo physicalInfo;
    private DailyConsumption dailyConsumption;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonWriterPhysicalInfo jsonWriter2;
    private JsonReaderPhysicalInfo jsonReader2;
    private JsonReader jsonReader;
    private JList list;
    private JButton removeButton;
    private static final String AddString = "Add";
    private static final String RemoveString = "Remove";
    private JTextField foodName;
    private DefaultListModel listModel;

    //EFFECTS: construct a daily consumption and runs the application
    static void createAndShowGUI() throws FileNotFoundException {
        //Create and set up the window.
        JFrame frame = new JFrame("food list");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new FitnessAppGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public FitnessAppGUI() throws FileNotFoundException {
        super(new BorderLayout());
        input = new Scanner(System.in);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter2 = new JsonWriterPhysicalInfo(JSON_STORE2);
        jsonReader2 = new JsonReaderPhysicalInfo(JSON_STORE2);


        //create the list and put it in a scroll pane.

        listModel = new DefaultListModel<>();
/*
        listModel.addElement("oats");
        listModel.addElement("egg");
        listModel.addElement("milk");

 */

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton(AddString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(AddString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        foodName = new JTextField(10);
        foodName.addActionListener(addListener);
        foodName.getDocument().addDocumentListener(addListener);

        removeButton = new JButton(RemoveString);
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(new RemoveListener());


        /*
        String name = listModel.getElementAt(
              list.getSelectedIndex()).toString();

         */

        //Create a panel that uses boxlayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(foodName);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }


    //runFitnessApp();


    public class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by actionlister
        public void actionPerformed(ActionEvent e) {
            String name = foodName.getText();
            //add user input to daily consumption's foodlist
            FoodItem inputFoodItem = new FoodItem(name, 100);
            dailyConsumption = new DailyConsumption("Today's Calories", 1500);
            dailyConsumption.addFoodItem(inputFoodItem);

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                foodName.requestFocusInWindow();
                foodName.selectAll();
                return;
            }
            /*
            int index = list.getSelectedIndex(); //get selected index

            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

             */


            listModel.insertElementAt(foodName.getText(), listModel.getSize());
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(foodName.getText());

            //Reset the text field.
            foodName.requestFocusInWindow();
            foodName.setText("");

            //Select the new item and make it visible.
            //list.setSelectedIndex(index);
            //list.ensureIndexIsVisible(index);
        }


        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();

        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }


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

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}



