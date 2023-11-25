package app.views;

import app.controllers.AboutController;
import app.controllers.ProfileController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 * 
 * @version 11.8.23
 * 
 * Application view class
 */
public class ApplicationView extends JFrame {
	/**
	 * String containing the app's name.
	 */
    private final String APPLICATION_NAME = "the App";
    /**
     * String containing the about button's name.
     */
    private final String ABOUT_BUTTON_NAME = "About";

    /**
     * String containing the profile button's name.
     */
    private final String PROFILE_BUTTON_NAME = "Profile";
    /**
     * Int containing width of app.
     */
    private final int appWidth = 500;
    /**
     * Int containing height of app.
     */
    private final int appHeight = 500;
    /**
     * Creates AboutController object.
     */
    private final AboutController aboutController;
    /**
     * Creates ProfileController object.
     */
    private final ProfileController profileController;
    /**
     * ApplicationView constructor.
     */
    public ApplicationView() {
        aboutController = new AboutController();
        profileController = new ProfileController();
        this.setTitle(APPLICATION_NAME);
        this.setSize(appWidth, appHeight);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(displayAboutBtn(), BorderLayout.NORTH);
        this.add(displayProfileBtn(), BorderLayout.CENTER);
    }
    /**
     * Creates about button.
     * @return
     */
    private JButton displayAboutBtn() {
        JButton aboutBtn = new JButton(ABOUT_BUTTON_NAME);
        aboutBtn.addActionListener(new AboutListener(this, aboutController, profileController));
        return aboutBtn;
    }
    /**
     * Creates a profile button
     * @return the button that opens the profile screen
     */
    private JButton displayProfileBtn() {
        JButton prfBtn = new JButton(PROFILE_BUTTON_NAME);
        prfBtn.addActionListener(new ProfileListener(this, aboutController, profileController));
        return prfBtn;
    }

    /**
     * returns app's width.
     * @return
     */
    public int getAppWidth() {
        return appWidth;
    }
    /**
     * returns app's height.
     * @return
     */
    public int getAppHeight() {
        return appHeight;
    }

    private static class AboutListener implements ActionListener {
    	/**
    	 * Creates ApplicationView object.
    	 */
        private final ApplicationView applicationView;
        /**
         * creates AboutController object.
         */
        private final AboutController aboutController;
        /**
         * Creates ProfileController object.
         */
        private final ProfileController profileController;

        /**
         * AboutListener constructor.
         * @param applicationView
         * @param aboutController
         * @param profileController
         */
        public AboutListener(
                ApplicationView applicationView,
                AboutController aboutController,
                ProfileController profileController) {
            this.applicationView = applicationView;
            this.aboutController = aboutController;
            this.profileController = profileController;
        }
        /**
         * opens the about screen.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            try {
                AboutScreen dialog = new AboutScreen(applicationView, aboutController, profileController);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private class ProfileListener implements ActionListener {
        /**
         * Creates ApplicationView object.
         */
        private final ApplicationView applicationView;
        /**
         * creates AboutController object.
         */
        private final AboutController aboutController;
        /**
         * Creates ProfileController object.
         */
        private final ProfileController profileController;

        /**
         * AboutListener constructor.
         * @param applicationView
         * @param aboutController
         * @param profileController
         */
        public ProfileListener(
                ApplicationView applicationView,
                AboutController aboutController,
                ProfileController profileController) {
            this.applicationView = applicationView;
            this.aboutController = aboutController;
            this.profileController = profileController;
        }

        /**
         * opens the about screen.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            try {
                //Makes new profile screen jFrame and closes the application view jFrame.
                ProfileScreen profileView = new ProfileScreen(appWidth, appHeight, this.profileController);
                profileView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                profileView.setVisible(true);
                this.applicationView.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
