package app.views;

import app.controllers.AboutController;
import app.controllers.ProfileController;

import javax.swing.*;

public final class AboutScreen extends JDialog {
    private final AboutController aboutController;
    private final ProfileController profileController;
    public AboutScreen(ApplicationView applicationView, AboutController aboutController,
                       ProfileController profileController) {
        this.aboutController = aboutController;
        this.profileController = profileController;
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(applicationView.getAppWidth(), applicationView.getAppHeight() / 2);
        this.setLocationRelativeTo(null);
        this.add(displayProfile());
    }

    private JLabel displayProfile() {
        JLabel text = new JLabel();
        text.setText("This App is Registered to: " + profileController.getName());
        return text;
    }
}