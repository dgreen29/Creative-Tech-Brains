package app.views;

import app.Main;
import app.controllers.ProfileController;

import javax.swing.*;

/**
 * A menu bar that is used to navigate to different sections of the app.
 * @author Zarif Mazumder
 */
public final class NavigationBar extends JMenuBar {
    /**
     * Default constructor that creates a bar with the default buttons.
     */
    public NavigationBar(ProfileController profileController) {
        setDoubleBuffered(true);
        JMenuItem budgetBtn = new JMenuItem("Budget");
        JMenuItem detailBtn = new JMenuItem("Detail");
        JMenuItem projectsBtn = new JMenuItem("Projects");
        JMenuItem profileBtn = new JMenuItem(profileController.getPrivilege() + " " + profileController.getName());
        JMenuItem aboutBtn = new JMenuItem("About");
        budgetBtn.addActionListener(e -> Main.setCurrentView(new BudgetView(profileController)));
        detailBtn.addActionListener(e -> Main.setCurrentView(new DetailView(profileController)));
        projectsBtn.addActionListener(e -> Main.setCurrentView(new ProjectsView(profileController)));
        profileBtn.addActionListener(e -> Main.setCurrentView(new ProfileScreen(profileController)));
        aboutBtn.addActionListener(e -> {
            AboutScreen dialog = new AboutScreen(profileController);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
        this.add(budgetBtn);
        this.add(detailBtn);
        this.add(projectsBtn);
        this.add(profileBtn);
        this.add(aboutBtn);
    }
}
