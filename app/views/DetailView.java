package app.views;

import app.Main;
import app.controllers.DetailController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the screen showing the details of a project.
 * @author Zarif Mazumder
 */
public class DetailView extends JFrame {
    private static final String TITLE_NAME = "Detail";
    private final DetailController detailController;
    private final ProfileController profileController;
    private final ProjectController projectController;

    public DetailView(ProfileController profileController) {
        this.profileController = profileController;
        projectController = profileController.getProjectController();
        detailController = new DetailController(projectController);
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new NavigationBar());
    }
}
