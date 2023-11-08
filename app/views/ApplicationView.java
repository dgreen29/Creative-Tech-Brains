package app.views;

import app.controllers.AboutController;
import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationView extends JFrame {
    private final String APPLICATION_NAME = "the App";
    private final String ABOUT_BUTTON_NAME = "About";
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
        this.add(displayAboutBtn(), BorderLayout.NORTH);
    }

    private JButton displayAboutBtn() {
        JButton aboutBtn = new JButton(ABOUT_BUTTON_NAME);
        aboutBtn.addActionListener(new AboutListener(this, aboutController, profileController));
        return aboutBtn;
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
}