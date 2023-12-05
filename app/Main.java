package app;

import app.views.ApplicationView;
import javax.swing.*;

/*
 * TCSS 360 B w/ Mr. Jeffrey Weiss
 * Authors: Darrell Green, Jr., Harman Singh, Vindhriko Chandran Cain, Zarif Mazumder
 */

/**
 * Driver class of the entire program.
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

    /**
     * @author Zarif Mazumder
     * @param view view
     */
    public static void setCurrentView(JFrame view) {
        currentView = view;
        currentView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentView.setVisible(true);
    }
}
