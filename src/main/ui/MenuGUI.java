package ui;

import model.DailyConsumption;
import model.PhysicalInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static ui.FitnessGoalEntry.chooseGoal;

public class MenuGUI extends JPanel implements ActionListener, FocusListener {

    private JTextField weightField;
    private JTextField heightField;
    private JTextField ageField;
    private PhysicalInfo physicalInfo;
    private StorageController storageController;
    private JSpinner genderSpinner;
    //private FitnessAppGUI fitnessAppGUI;
    //private final static int GAP = 10;

    static void displayUserMenu(PhysicalInfo physicalInfo, StorageController storageController) {
        //Create and set up the window.
        JFrame frame = new JFrame("Please input your physical information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.

        MenuGUI newContentPane = new MenuGUI(physicalInfo, storageController);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public MenuGUI(PhysicalInfo physicalInfo, StorageController storageController) {

        this.physicalInfo = physicalInfo;
        this.storageController = storageController;

        //setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setPreferredSize(new Dimension(2000, 400));

        this.add(createEntryFields());
        this.add(createButtons());

    }

    protected JComponent createButtons() {
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        //buttonPane.setLayout();

        JButton button = new JButton("Next");
        button.addActionListener(this);
        buttonPane.add(button);


        ActionListener b1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //double parseMyWeight = 0;
                //int parseMyHeight = 0;
                //int parseMyAge = 0;
                //boolean parseMyGender = false;
                initializeInfo(e, button);

                storageController.savePhysicalInfo(physicalInfo);
                chooseGoal(physicalInfo, storageController);
            }
        };
        button.addActionListener(b1Listener);

        return buttonPane;
    }

    private void initializeInfo(ActionEvent e, JButton button) {
        double parseMyWeight;
        boolean parseMyGender;
        int parseMyAge;
        int parseMyHeight;
        if (e.getSource() == button) {
            String myWeight = weightField.getText();
            parseMyWeight = Double.parseDouble(myWeight);
            String myHeight = heightField.getText();
            parseMyHeight = Integer.parseInt(myHeight);
            String myAge = weightField.getText();
            parseMyAge = Integer.parseInt(myAge);
            String genderString = genderSpinner.toString();
            if (genderString == "female") {
                parseMyGender = true;
            } else {
                parseMyGender = false;
            }


            physicalInfo.setPhysicalInfo(parseMyWeight, parseMyHeight, parseMyAge, parseMyGender);
        }
    }

    public JComponent createEntryFields() {
        JPanel panel = new JPanel();


        String[] labelStrings = {
                "Weight in kg: ",
                "Height in cm: ",
                "Age: ",
                "gender: ",

        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
        fieldNum = weightField(fields, fieldNum);

        heightField = new JTextField();
        heightField.setColumns(20);
        fields[fieldNum++] = heightField;

        ageField = new JTextField();
        ageField.setColumns(20);
        fields[fieldNum++] = ageField;


        String[] genderStrings = getGenderStrings();
        genderSpinner = new JSpinner((new SpinnerListModel(genderStrings)));
        fields[fieldNum++] = genderSpinner;

        //associate label/field pairs, add everything
        matchLabels(panel, labelStrings, labels, fields);
        return panel;
    }

    private int weightField(JComponent[] fields, int fieldNum) {
        weightField = new JTextField();
        weightField.setColumns(20);
        fields[fieldNum++] = weightField;
        return fieldNum;
    }

    private void matchLabels(JPanel panel, String[] labelStrings, JLabel[] labels, JComponent[] fields) {
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                    JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //add listeners to each field
            JTextField tf = null;
            if (fields[i] instanceof JSpinner) {
                tf = getTextField((JSpinner) fields[i]);
            } else {
                tf = (JTextField) fields[i];
            }
            tf.addActionListener(this);
            tf.addFocusListener(this);


        }
    }

    public String[] getGenderStrings() {
        String[] genderStrings = {
                "Female",
                "Male"
        };
        return genderStrings;
    }

    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                    + spinner.getEditor().getClass()
                    + " isn't a descendant of DefaultEditor");
            return null;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }


}
