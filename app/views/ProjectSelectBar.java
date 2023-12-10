package app.views;

import app.Main;
import app.controllers.ProfileController;
import app.models.Profile;
import app.models.Project;

import javax.swing.*;
import javax.swing.border.BevelBorder;

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
     * @param profile current profile
     */
    private void displayProjects(Profile profile) {
        // Create "+" button before adding project buttons
        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            // Open dialog to enter the new project name
            String newProjectName = JOptionPane.showInputDialog("Enter the name of the new Project").trim();

            // Create new Project and add it to the profile
            if (newProjectName.isEmpty()) {
                profileController.createProject(newProjectName);

                // redraw the select bar to show the newly added project
                displayProjects(profile);
                this.revalidate();
                this.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Project name cannot be empty. Please try again.");
            }
        });

        // Add project buttons
        for (Project project : profile.getProjects()) {
            this.add(new JButton(project.getName()));
        }

        // Add "+" button after all project buttons have been added
        this.add(addButton);
    }
}

