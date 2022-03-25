package ui;

import model.DailyConsumption;
import model.PhysicalInfo;
import ui.StorageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import static ui.AddFoodItemGUI.createAndShowGUI;
//import static ui.FitnessAppGUI.createAndShowGUI;
import static ui.MenuGUI.displayUserMenu;

public class EntryMenu extends JPanel implements ActionListener {
    private JFrame frame;
    private JLabel displayField;
    private ImageIcon image;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private PhysicalInfo physicalInfo;
    private DailyConsumption dailyConsumption;
    private StorageController storageController;


    //Cite from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html button demo
    public static void showMenuEntry() throws FileNotFoundException {

        //Create and set up the window.
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        EntryMenu newContentPane = new EntryMenu();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);


        //frame.add

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public EntryMenu() throws FileNotFoundException {

        physicalInfo = new PhysicalInfo(0, 0, 0, false, 0);
        dailyConsumption = new DailyConsumption("Today's Calories", 1500);
        storageController = new StorageController();

        b1 = new JButton("Register my physical information");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setMnemonic(KeyEvent.VK_D);

        b2 = new JButton("Add a food item");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_D);

        b3 = new JButton("Show remaining calories");
        b3.setVerticalTextPosition(AbstractButton.CENTER);
        b3.setMnemonic(KeyEvent.VK_D);

        b4 = new JButton("Load my profile");
        b4.setVerticalTextPosition(AbstractButton.CENTER);
        b4.setMnemonic(KeyEvent.VK_D);

        image = new ImageIcon("./data/image.png");

        displayField = new JLabel(image);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(displayField);

        listener();


    }


    private void listener() {
        ActionListener b1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUserMenu(physicalInfo, storageController);
            }
        };
        b1.addActionListener(b1Listener);

        b2Listener();

        ActionListener b3Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                displayRemainingCalories();

            }
        };
        b3.addActionListener(b3Listener);

        labelListener();
    }

    private void b2Listener() {
        ActionListener b2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createAndShowGUI(dailyConsumption, storageController);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        b2.addActionListener(b2Listener);
    }

    private void labelListener() {
        ActionListener b4Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();


                JLabel label = new JLabel();
                label.setText("You have consumed: " + dailyConsumption.getFoodItem());
                label.setBounds(0, 30, 400, 50);
                JLabel physicalInfoLabel = new JLabel();
                physicalInfoLabel.setText(physicalInfo.toString());

                        /*
                        "<html>" + "Weight: " + physicalInfo.getWeight() + "<br>"
                        + "Height: " + physicalInfo.getHeight() + "<br>"
                        + "Gender :" + physicalInfo.getGender() + "<br>" + "Age: " + physicalInfo.getAge() + "</html>");

                         */

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
                frame.setVisible(true);
                frame.add(label);
                frame.add(physicalInfoLabel);
                //frame.pack();

            }
        };
        b4.addActionListener(b4Listener);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }


    public void displayRemainingCalories() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("your remaining calories is");
        JLabel label1 = new JLabel(Double.toString(dailyConsumption.getRemainingCalories()));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 100));
        panel.add(label);
        panel.add(label1);

        frame.getContentPane().add(panel);
        frame.pack();


        frame.setVisible(true);

    }


}
