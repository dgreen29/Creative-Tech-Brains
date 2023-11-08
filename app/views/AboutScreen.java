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
        this.add(displayTeam());
    }

    private JLabel displayProfile() {
        JLabel heading = new JLabel();
        heading.setText("This App is Registered to: " + profileController.getName());
        return heading;
    }

    private JLabel displayTeam() {
        JLabel heading = new JLabel();
        String[] team = aboutController.getTeam();
        heading.setText("This app provided by: " + String.join(",", team));
        return heading;
    }
}