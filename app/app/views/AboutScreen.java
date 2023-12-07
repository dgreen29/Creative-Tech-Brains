package app.views;

import app.Main;
import app.controllers.AboutController;
import app.controllers.ProfileController;
import javax.swing.*;
import java.awt.*;

/**
 * Displays the team and version info.
 * @author Zarif Mazumder
 */
public final class AboutScreen extends JDialog {
    private static final String TITLE_NAME = "About";
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
        northPanel.add(displayProfile(), BorderLayout.WEST);
        northPanel.add(displayVersion(), BorderLayout.EAST);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(displayTeam(), BorderLayout.CENTER);
    }

    /**
     * @author Zarif Mazumder
     * @return profile <code>JLabel</code>
     */
    private JLabel displayProfile() {
        JLabel heading = new JLabel();
        heading.setText("This app is registered to: " + Main.getName());
        return heading;
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
}
