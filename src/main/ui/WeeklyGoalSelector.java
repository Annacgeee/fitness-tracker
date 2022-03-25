package ui;

import model.PhysicalInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import java.util.jar.JarEntry;

import static ui.EntryMenu.showMenuEntry;
import static ui.FitnessAppGUI.createAndShowGUI;
import static ui.MenuGUI.displayUserMenu;

public class WeeklyGoalSelector extends JPanel implements ActionListener {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private PhysicalInfo physicalInfo;

    public static void chooseWeeklyGoal() {
        JFrame frame = new JFrame("Please choose your goal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        WeeklyGoalSelector newContentPane = new WeeklyGoalSelector();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public WeeklyGoalSelector() {
        b1 = new JButton("I want to lose 0.2 kg per week");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setMnemonic(KeyEvent.VK_D);

        b2 = new JButton("I want to lose 0.5 kg per week");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_D);

        b3 = new JButton("I want to lose 0.8 kg per week");
        b3.setVerticalTextPosition(AbstractButton.CENTER);
        b3.setMnemonic(KeyEvent.VK_D);

        b4 = new JButton("I want to lose 1 kg per week");
        b4.setVerticalTextPosition(AbstractButton.CENTER);
        b4.setMnemonic(KeyEvent.VK_D);

        b5 = new JButton("Back to main page");
        b5.setVerticalTextPosition(AbstractButton.CENTER);
        b5.setMnemonic(KeyEvent.VK_D);


        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);

        ActionListener b1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicalInfo.calculateCaloriesToLoseWeight(1);
                System.out.println("you are all set!you can close this page");

            }
        };
        b1.addActionListener(b1Listener);

        ActionListener b2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicalInfo.calculateCaloriesToLoseWeight(2);
                System.out.println("you are all set!you can close this page");
            }
        };
        b2.addActionListener(b2Listener);

        ActionListener b3Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              physicalInfo.calculateCaloriesToLoseWeight(3);
                System.out.println("you are all set! you can close this page");
            }
        };
        b3.addActionListener(b3Listener);

        ActionListener b4Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicalInfo.calculateCaloriesToLoseWeight(4);
                System.out.println("you are all set!you can close this page");
            }
        };
        b4.addActionListener(b4Listener);

        ActionListener b5Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenuEntry();
            }
        };
        b5.addActionListener(b5Listener);


    }








    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
