package app.views;

import app.Main;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;

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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Don't forget to handle window closing
        NavigationBar navigationBar = new NavigationBar();
        navigationBar.setDoubleBuffered(true);
        this.setJMenuBar(navigationBar);
        ProjectSelectBar projectSelectBar = new ProjectSelectBar();
        projectSelectBar.setDoubleBuffered(true);
        this.add(projectSelectBar, BorderLayout.SOUTH);
    }
}