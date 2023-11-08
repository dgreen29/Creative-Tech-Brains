package app.views;

import app.controllers.AboutController;
import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;

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
        this.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(displayProfile(), BorderLayout.WEST);
        northPanel.add(displayVersion(), BorderLayout.EAST);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(displayTeam(), BorderLayout.CENTER);
    }

    private JLabel displayProfile() {
        JLabel heading = new JLabel();
        heading.setText("This App is Registered to: " + profileController.getName());
        return heading;
    }

    private JLabel displayTeam() {
        JLabel text = new JLabel();
        String[] team = aboutController.getTeam();
        text.setText("This app provided by: " + String.join(",", team));
        return text;
    }

    private JLabel displayVersion() {
        JLabel text = new JLabel();
        text.setText("Version " + aboutController.getVersion());
        return text;
    }
}