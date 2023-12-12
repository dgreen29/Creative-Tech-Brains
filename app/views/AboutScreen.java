package app.views;

import app.Main;
import app.controllers.AboutController;
import app.controllers.ProfileController;
import app.models.Profile;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the team and version info.
 * @author Zarif Mazumder
 */
public final class AboutScreen extends JDialog {
    private static final String TITLE_NAME = "About";
    private boolean privilegeChanged = false;
    private final AboutController aboutController;
    private final ProfileController profileController;

    public AboutScreen(ProfileController profileController) {
        aboutController = new AboutController();
        this.profileController = profileController;
        this.setTitle(TITLE_NAME);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(Main.APP_WIDTH + 200, Main.APP_HEIGHT / 2);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(displayVersion(), BorderLayout.EAST);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(displayTeam(), BorderLayout.CENTER);
        this.add(displayPrivilegeButtons(), BorderLayout.SOUTH);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (privilegeChanged) {
                    Main.setCurrentView(new ProfileScreen(profileController));
                }
            }
        });
    }

    /**
     * @author Zarif Mazumder
     * @return team <code>JLabel</code>
     */
    private JLabel displayTeam() {
        JLabel text = new JLabel();
        String[] team = aboutController.getTeam();
        text.setText("This app provided by: " + String.join(", ", team));
        return text;
    }

    /**
     * @author Zarif Mazumder
     * @return version <code>JLabel</code>
     */
    private JLabel displayVersion() {
        JLabel text = new JLabel();
        text.setText("Version: " + aboutController.getVersion());
        return text;
    }

    /**
     * @author Zarif Mazumder
     * @return change privilege buttons
     */
    private JPanel displayPrivilegeButtons() {
        JPanel panel = new JPanel();
        JButton userBtn = new JButton("Become User");
        userBtn.addActionListener(e -> {
            profileController.setPrivilege(Profile.Privilege.USER);
            privilegeChanged = true;
        });
        JButton adminBtn = new JButton("Become Admin");
        adminBtn.addActionListener(e -> {
            profileController.setPrivilege(Profile.Privilege.ADMIN);
            privilegeChanged = true;
        });
        panel.add(userBtn);
        panel.add(adminBtn);
        return panel;
    }
}
