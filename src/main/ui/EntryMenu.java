package ui;

import model.PhysicalInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import static ui.FitnessAppGUI.createAndShowGUI;
import static ui.MenuGUI.displayUserMenu;

public class EntryMenu extends JPanel implements ActionListener {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    public static void showMenuEntry() {

        //Create and set up the window.
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        EntryMenu newContentPane = new EntryMenu();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public EntryMenu() {
        b1 = new JButton("Register my physical information");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setMnemonic(KeyEvent.VK_D);

        b2 = new JButton("Add a food item");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_D);

        b3 = new JButton("Load My Food List");
        b3.setVerticalTextPosition(AbstractButton.CENTER);
        b3.setMnemonic(KeyEvent.VK_D);

        b4 = new JButton("Save my profile");
        b4.setVerticalTextPosition(AbstractButton.CENTER);
        b4.setMnemonic(KeyEvent.VK_D);
/*
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

 */


        add(b1);
        add(b2);
        add(b3);
        add(b4);

//}
    //public void setUpButtonListener() {
        ActionListener b1Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUserMenu();
            }
        };
        b1.addActionListener(b1Listener);

        ActionListener b2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createAndShowGUI();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        b2.addActionListener(b2Listener);

        ActionListener b3Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("loading....");
            }
        };
        b3.addActionListener(b3Listener);

        ActionListener b4Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("saving");
            }
        };
        b4.addActionListener(b4Listener);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}
