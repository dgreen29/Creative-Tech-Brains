package app.views;

import app.Main;
import app.controllers.AboutController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays the main menu of the application.
 * @author Darrell Green Jr., Harman Singh, Zarif Mazumder
 */
public class ApplicationView extends JFrame {
    private static final String TITLE_NAME = "the App";
    private static final String ABOUT_BUTTON_NAME = "About";
    private static final String PROFILE_BUTTON_NAME = "Profile";
    private final int appWidth = 500;
    private final int appHeight = 500;
    private final AboutController aboutController;
    private final ProfileController profileController;
    private final ProjectController projectController;

    public ApplicationView() {
        aboutController = new AboutController();
        profileController = new ProfileController();
        projectController = profileController.getProjectController();
        this.setTitle(TITLE_NAME);
        this.setSize(appWidth, appHeight);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(displayAboutButton(), BorderLayout.NORTH);
        this.add(displayProfileButton(), BorderLayout.CENTER);
    }

    /**
     * @author Zarif Mazumder
     * @return about <code>JButton</code>
     */
    private JButton displayAboutButton() {
        JButton aboutBtn = new JButton(ABOUT_BUTTON_NAME);
        aboutBtn.addActionListener(new AboutListener(this, aboutController, profileController));
        return aboutBtn;
    }

    /**
     * @author Harman Singh
     * @return about <code>JButton</code>
     */
    private JButton displayProfileButton() {
        JButton prfBtn = new JButton(PROFILE_BUTTON_NAME);
        prfBtn.addActionListener(new ProfileListener(profileController));
        return prfBtn;
    }

    /**
     * @author Zarif Mazumder
     * @return <code>JFrame</code> width in pixels
     */
    public int getAppWidth() {
        return appWidth;
    }

    /**
     * @author Zarif Mazumder
     * @return <code>JFrame</code> height in pixels
     */
    public int getAppHeight() {
        return appHeight;
    }

    /**
     * @author Harman Singh, Zarif Mazumder
     */
    private record AboutListener(ApplicationView applicationView, AboutController aboutController,
                                 ProfileController profileController) implements ActionListener {

        /**
         * Opens <code>AboutScreen</code>.
         * @author Harman Singh, Zarif Mazumder
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

    /**
     * @author Harman Singh
     */
    private class ProfileListener implements ActionListener {
        private final ProfileController profileController;

        public ProfileListener(
                ProfileController profileController) {
            this.profileController = profileController;
        }

        /**
         * Opens <code>ProfileScreen</code>.
         * @author Harman Singh
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
