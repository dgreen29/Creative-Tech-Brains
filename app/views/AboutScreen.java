package app.views;

import app.controllers.AboutController;
import app.controllers.ProfileController;
import javax.swing.*;
import java.awt.*;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 * 
 * @version 11.8.23
 * 
 * About screen class
 */
public final class AboutScreen extends JDialog {
    private final String ABOUT = "About";
	/**
	 * Creates AboutController object.
	 */
    private final AboutController aboutController;
    /**
     * Creates ProfileContoller object.
     */
    private final ProfileController profileController;
    /**
     * Creates an about screen with the provided information from
     * parameters.
     * @param applicationView
     * @param aboutController
     * @param profileController
     */
    public AboutScreen(ApplicationView applicationView, AboutController aboutController,
                       ProfileController profileController) {
        this.aboutController = aboutController;
        this.profileController = profileController;
        this.setTitle(ABOUT);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(applicationView.getAppWidth()+200, applicationView.getAppHeight() / 2);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(displayProfile(), BorderLayout.WEST);
        northPanel.add(displayVersion(), BorderLayout.EAST);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(displayTeam(), BorderLayout.CENTER);
    }
    /**
     * Displays current profile to the about screen.
     * @return heading
     */
    private JLabel displayProfile() {
        JLabel heading = new JLabel();
        heading.setText("This app is registered to: " + profileController.getName());
        return heading;
    }
    /**
     * Displays the development teams information.
     * @return text
     */
    private JLabel displayTeam() {
        JLabel text = new JLabel();
        String[] team = aboutController.getTeam();
        text.setText("This app provided by: " + String.join(", ", team));
        return text;
    }
    /**
     * Displays current Version.
     * @return text
     */
    private JLabel displayVersion() {
        JLabel text = new JLabel();
        text.setText("Version: " + aboutController.getVersion());
        return text;
    }
}
