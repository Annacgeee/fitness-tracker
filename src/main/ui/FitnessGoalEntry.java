package ui;

import model.DailyConsumption;
import model.Event;
import model.EventLog;
import model.PhysicalInfo;

import javax.swing.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Iterator;

//import static ui.FitnessAppGUI.createAndShowGUI;
import static ui.MenuGUI.displayUserMenu;
import static ui.WeeklyGoalSelector.chooseWeeklyGoal;
//Cite from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html button demo

public class FitnessGoalEntry extends JPanel implements ActionListener {
    private JButton b1;
    private JButton b2;
//    private MenuGUI menuGUI;
//    private PhysicalInfo physicalInfo;
//    private DailyConsumption dailyConsumption;
//    private StorageController storageController;
    // effects: create entry

    public static void chooseGoal(PhysicalInfo physicalInfo, StorageController storageController) {
        JFrame frame = new JFrame("Please choose your goal");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Create and set up the content pane.
        FitnessGoalEntry newContentPane = new FitnessGoalEntry(physicalInfo, storageController);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Iterator<Event> eventIter = EventLog.getInstance().iterator();
                while (eventIter.hasNext()) {
                    System.out.println(eventIter.next().toString());
                }
                System.exit(0);
            }
        });

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // effects: choose fitness goal GUI
    public FitnessGoalEntry(PhysicalInfo physicalInfo, StorageController storageController) {
//        this.physicalInfo = physicalInfo;
//        this.storageController = storageController;

        b1 = new JButton("I want to maintain weight");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setMnemonic(KeyEvent.VK_D);

        b2 = new JButton("I want to loose weight");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_D);


        add(b1);
        add(b2);

        listeners(physicalInfo, storageController);

    }

    //EFFECTS: actionlistener for the buttons
    private void listeners(PhysicalInfo physicalInfo, StorageController storageController) {
        ActionListener b1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();


                JLabel label = new JLabel();
                label.setText("Your daily max calories is " + physicalInfo.calculateCaloriesNeededForMaintainWeight());

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setVisible(true);
                frame.add(label);

            }
        };
        b1.addActionListener(b1Listener);

        ActionListener b2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseWeeklyGoal(physicalInfo, storageController);
            }
        };
        b2.addActionListener(b2Listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}



