package ui;

import model.PhysicalInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;



import static ui.EntryMenu.showMenuEntry;

//for user to select weekly fitness goal GUI
public class WeeklyGoalSelector extends JPanel implements ActionListener {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;


    public static void chooseWeeklyGoal(PhysicalInfo physicalInfo, StorageController storageController) {
        JFrame frame = new JFrame("Please choose your goal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        WeeklyGoalSelector newContentPane = new WeeklyGoalSelector(physicalInfo, storageController);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: create goal seletor

    public WeeklyGoalSelector(PhysicalInfo physicalInfo, StorageController storageController) {
        createButtons();

        b1ActionListener(physicalInfo, storageController);

        b2ActionListener(physicalInfo, storageController);

        b3ActionListener(physicalInfo, storageController);

        b4ActionListener(physicalInfo, storageController);

        b5ActionListener();


    }

    //EFFECTS: actionlistener for button
    private void b5ActionListener() {
        ActionListener b5Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showMenuEntry();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        b5.addActionListener(b5Listener);
    }

    // //EFFECTS: actionlistener for button


    private void b4ActionListener(PhysicalInfo physicalInfo, StorageController storageController) {
        ActionListener b4Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();


                JLabel label = new JLabel();
                label.setText("Your daily max calories is " + physicalInfo.calculateCaloriesToLoseWeight(4));
                storageController.savePhysicalInfo(physicalInfo);


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setVisible(true);
                frame.add(label);
            }
        };
        b4.addActionListener(b4Listener);
    }

    //EFFECTS: actionlistener for button

    private void b3ActionListener(PhysicalInfo physicalInfo, StorageController storageController) {
        ActionListener b3Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFrame frame = new JFrame();


                JLabel label = new JLabel();
                label.setText("Your daily max calories is " + physicalInfo.calculateCaloriesToLoseWeight(3));
                storageController.savePhysicalInfo(physicalInfo);


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setVisible(true);
                frame.add(label);
            }
        };
        b3.addActionListener(b3Listener);
    }

    //EFFECTS: actionlistener for button

    private void b2ActionListener(PhysicalInfo physicalInfo, StorageController storageController) {
        ActionListener b2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();


                JLabel label = new JLabel();
                label.setText("Your daily max calories is " + physicalInfo.calculateCaloriesToLoseWeight(2));
                storageController.savePhysicalInfo(physicalInfo);


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setVisible(true);
                frame.add(label);
            }
        };
        b2.addActionListener(b2Listener);
    }

    //EFFECTS: actionlistener for button

    private void b1ActionListener(PhysicalInfo physicalInfo, StorageController storageController) {
        ActionListener b1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();


                JLabel label = new JLabel();
                label.setText("Your daily max calories is " + physicalInfo.calculateCaloriesToLoseWeight(1));
                storageController.savePhysicalInfo(physicalInfo);


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setVisible(true);
                frame.add(label);


            }
        };
        b1.addActionListener(b1Listener);
    }
    
    //  //EFFECTS: create all buttons

    private void createButtons() {
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
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
