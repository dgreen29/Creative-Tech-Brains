package app.views;

import app.Main;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the main menu of the application.
 * @author Darrell Green Jr., Harman Singh, Zarif Mazumder
 */
public class ApplicationView extends JFrame {
    private static final String TITLE_NAME = "Projects";
    private final ProfileController profileController;
    private final ProjectController projectController;

    public ApplicationView(ProfileController profileController) {
        this.profileController = profileController;
        projectController = profileController.getProjectController();
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        NavigationBar navigationBar = new NavigationBar(profileController);
        this.setJMenuBar(navigationBar);
        ProjectSelectBar projectSelectBar = new ProjectSelectBar(profileController);
        this.add(projectSelectBar, BorderLayout.SOUTH);
    }
}
