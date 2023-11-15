package app;

import app.views.ApplicationView;
import javax.swing.*;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 *
 * @version 11.8.23
 *
 * Program Purpose: The purpose of this program is to satisfy the
 * Project UI and About Screen requirements laid out in the project
 * description as referenced by the Client Interview.
 */

/**
 * This is the Main driver class of the entire program.
 */
public class Main {

    /**
     * The main method is the driver method for the entire program.
     * @param args A generic String array that gets passed into the
     *             method by default.
     */
    public static void main(String[] args) {
        displayApplicationView();
    }

    private static void displayApplicationView() {
        /*
        Creates a new J Frame and initializes it.
         */
        JFrame frame = new ApplicationView();

        /*
        Sets the default close operation on the frame.
         */
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*
        The visibility here is set to true in order for the frame to be
        displayed on the screen.
         */
        frame.setVisible(true);
    }
}
