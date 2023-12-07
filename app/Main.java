package app;

import app.controllers.ProfileController;
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
    public final static int APP_HEIGHT = 500;
    public final static int APP_WIDTH = 500;
    private static JFrame currentView;
    private static ProfileController profileController;

    /**
     * Driver method.
     * @param args A generic String array that gets passed into the
     *             method by default.
     */
    public static void main(String[] args) {
        profileController = new ProfileController();
        setCurrentView(new ApplicationView(profileController));
    }

    public static ProfileController getProfileController() {
        return profileController;
    }

    /**
     * @author Zarif Mazumder
     * @param view view
     */
    public static void setCurrentView(JFrame view) {
        if (currentView != null) {
            currentView.dispose();
        }
        currentView = view;
        currentView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentView.setVisible(true);
    }
}
