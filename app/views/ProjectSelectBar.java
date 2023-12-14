package app.views;

import app.Main;
import app.controllers.ProfileController;
import app.models.ProfileModel;
import app.models.Project;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Displays all projects that can be switched to.
 * @author Darrell Green, Jr., Zarif Mazumder
 */
public final class ProjectSelectBar extends JPanel {
    private final ProfileController profileController;

    public ProjectSelectBar(ProfileController profileController) {
        setDoubleBuffered(true);
        this.profileController = profileController;
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        displayProjects(profileController.getProfile());
    }

    /**
     * @author Darrell Green, Jr., Zarif Mazumder
     * @param profileModel current profile
     */
    private void displayProjects(ProfileModel profileModel) {
        // Add project buttons
        for (Project project : profileModel.getProjects()) {
            JButton projectBtn = displayProjectButton(project);
            this.add(projectBtn);
        }

        // Add "+" button after all project buttons have been added
        if (profileController.getPrivilege() == ProfileModel.Privilege.ADMIN) {
            JButton addButton = displayAddButton();
            this.add(addButton);
        }
    }

    /**
     * @author Zarif Mazumder
     * @param project <code>Project</code>
     * @return project as button
     */
    private JButton displayProjectButton(Project project) {
        String projectName = project.getName();
        JButton projectBtn = new JButton(projectName);
        if (projectName.equals(profileController.getProjectController().getProject().getName())) {
            projectBtn.setFont(projectBtn.getFont().deriveFont(Font.BOLD));
        }
        projectBtn.addActionListener(e -> {
            profileController.getProjectController().setCurrentProject(project);
            Main.setCurrentView(new JournalView(profileController));
        });
        return projectBtn;
    }

    /**
     * @author Darrell Green, Jr., Zarif Mazumder
     * @return add button
     */
    private JButton displayAddButton() {
        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            // Open dialog to enter the new project name
            String newProjectName = JOptionPane.showInputDialog("Enter the name of the new Project").trim();

            // Create new Project and add it to the profile
            if (!newProjectName.isEmpty()) {
                profileController.createProject(newProjectName);
                Main.setCurrentView(new JournalView(profileController));
            } else {
                JOptionPane.showMessageDialog(null, "Project name cannot be empty. Please try again.");
            }
        });
        return addButton;
    }
}

