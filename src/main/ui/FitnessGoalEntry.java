package ui;

import model.PhysicalInfo;

import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import static ui.FitnessAppGUI.createAndShowGUI;
import static ui.MenuGUI.displayUserMenu;
import static ui.WeeklyGoalSelector.chooseWeeklyGoal;

public class FitnessGoalEntry extends JPanel implements ActionListener {
    private JButton b1;
    private JButton b2;
    private MenuGUI menuGUI;

    public static void chooseGoal() {
        JFrame frame = new JFrame("Please choose your goal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        FitnessGoalEntry newContentPane = new FitnessGoalEntry();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public FitnessGoalEntry() {
        b1 = new JButton("I want to maintain weight");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setMnemonic(KeyEvent.VK_D);

        b2 = new JButton("I want to loose weight");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_D);


        add(b1);
        add(b2);

        ActionListener b1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();


                JLabel label = new JLabel();
                label.setText("Your daily max calories is ");




                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,500);
                frame.setVisible(true);
                frame.add(label);

            }
        };
        b1.addActionListener(b1Listener);

        ActionListener b2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseWeeklyGoal();
            }
        };
        b2.addActionListener(b2Listener);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


