package ui;

import java.io.FileNotFoundException;

import static ui.FitnessAppGUI.createAndShowGUI;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createAndShowGUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

