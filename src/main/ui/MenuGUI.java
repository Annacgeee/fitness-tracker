package ui;

import javafx.scene.shape.Box;
import model.PhysicalInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MenuGUI extends JPanel implements ActionListener, FocusListener {

    private JTextField weightField;
    private JTextField heightField;
    private JTextField ageField;
    private JButton button;
    private PhysicalInfo physicalInfo;
    private JSpinner genderSpinner;
    private final static int GAP = 10;

    static void displayUserMenu() {
        //Create and set up the window.
        JFrame frame = new JFrame("Please input your physical information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Add contents to the window.
        frame.add(new MenuGUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public MenuGUI() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        JPanel panel = new JPanel();


        panel.setLayout(new BoxLayout(panel,
                BoxLayout.LINE_AXIS));
        panel.add(createEntryFields());
        panel.add(createButtons());

    }

    protected JComponent createButtons() {
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        //buttonPane.setLayout();

        JButton button = new JButton("Next");
        button.addActionListener(this);
        buttonPane.add(button);


        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0,
                GAP - 5, GAP - 5));
        return buttonPane;
    }

    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());


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
        weightField = new JTextField();
        weightField.setColumns(20);
        fields[fieldNum++] = weightField;

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
        // and lay it out
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
        return panel;
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
        double parseMyWeight = 0;
        int parseMyHeight = 0;
        int parseMyAge = 0;
        boolean parseMyGender = false;
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

            physicalInfo = new PhysicalInfo(parseMyWeight,parseMyHeight,parseMyAge,parseMyGender);
        }

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
