package app;

import app.views.ApplicationView;
import javax.swing.*;

/*
 * Authors: Darrell Green, Jr., Harman Singh, Vindhriko Chandran Cain, Zarif Mazumder
 */

/**
 * This is the Main driver class of the entire program.
 */
public class Main {
    private static JFrame currentView;

    /**
     * Driver method.
     * @param args A generic String array that gets passed into the
     *             method by default.
     */
    public static void main(String[] args) {
        setCurrentView(new ApplicationView());
    }

    public static void setCurrentView(JFrame view) {
        currentView = view;
        currentView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentView.setVisible(true);
    }
}
