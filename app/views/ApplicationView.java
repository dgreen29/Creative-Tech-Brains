package app.views;

import app.Main;
import app.controllers.AboutController;
import app.controllers.ProfileController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Authors: Darrell Green Jr., Harman Singh, Zarif Mazumder
 */

/**
 * Display's the main menu of the application.
 */
public class ApplicationView extends JFrame {
    private static final String APPLICATION_NAME = "the App";
    private static final String ABOUT_BUTTON_NAME = "About";
    private static final String PROFILE_BUTTON_NAME = "Profile";
    private final int appWidth = 500;
    private final int appHeight = 500;
    private final AboutController aboutController;
    private final ProfileController profileController;

    public ApplicationView() {
        aboutController = new AboutController();
        profileController = new ProfileController();
        this.setTitle(APPLICATION_NAME);
        this.setSize(appWidth, appHeight);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(displayAboutButton(), BorderLayout.NORTH);
        this.add(displayProfileButton(), BorderLayout.CENTER);
    }

    private JButton displayAboutButton() {
        JButton aboutBtn = new JButton(ABOUT_BUTTON_NAME);
        aboutBtn.addActionListener(new AboutListener(this, aboutController, profileController));
        return aboutBtn;
    }

    private JButton displayProfileButton() {
        JButton prfBtn = new JButton(PROFILE_BUTTON_NAME);
        prfBtn.addActionListener(new ProfileListener(profileController));
        return prfBtn;
    }

    public int getAppWidth() {
        return appWidth;
    }

    public int getAppHeight() {
        return appHeight;
    }

    private static class AboutListener implements ActionListener {
        private final ApplicationView applicationView;
        private final AboutController aboutController;
        private final ProfileController profileController;

        public AboutListener(
                ApplicationView applicationView,
                AboutController aboutController,
                ProfileController profileController) {
            this.applicationView = applicationView;
            this.aboutController = aboutController;
            this.profileController = profileController;
        }
        /**
         * Opens <code>AboutScreen</code>.
         * @param e <code>ActionEvent</code>
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
        private final ProfileController profileController;

        public ProfileListener(
                ProfileController profileController) {
            this.profileController = profileController;
        }

        /**
         * Opens <code>ProfileScreen</code>.
         * @param e <code>ActionEvent</code>
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Main.setCurrentView(new ProfileScreen(appWidth, appHeight, this.profileController));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
