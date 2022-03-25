package ui;

import model.DailyConsumption;
import model.FoodItem;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AddFoodItemGUI extends JPanel
                            implements ListSelectionListener {
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
        JComponent newContentPane = new AddFoodItemGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public AddFoodItemGUI() {
        listModel = new DefaultListModel<>();

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton(AddString);
        AddFoodItemGUI.AddListener addListener = new AddFoodItemGUI.AddListener(addButton);
        addButton.setActionCommand(AddString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        foodName = new JTextField(10);
        foodName.addActionListener(addListener);
        foodName.getDocument().addDocumentListener(addListener);

        removeButton = new JButton(RemoveString);
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(new AddFoodItemGUI.RemoveListener());

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

    @Override
    public void valueChanged(ListSelectionEvent e) {

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
            //dailyConsumption.addFoodItem(inputFoodItem);

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
}
