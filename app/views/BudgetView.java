package app.views;

import app.Main;
import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the screen showing the <code>Budget</code> of a project.
 * @author Zarif Mazumder
 */
public class BudgetView extends JFrame {
    private static final String TITLE_NAME = "Budget";
    private final BudgetController budgetController;
    private final ProfileController profileController;
    private final ProjectController projectController;

    public BudgetView(ProfileController profileController) {
        this.profileController = profileController;
        projectController = profileController.getProjectController();
        budgetController = new BudgetController(projectController);
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new NavigationBar());
        this.add(new ProjectSelectBar(), BorderLayout.SOUTH);
    }
}
