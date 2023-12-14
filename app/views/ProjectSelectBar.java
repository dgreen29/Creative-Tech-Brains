package app.views;

import app.Main;
import app.models.Profile;
import app.models.Project;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a panel that displays a select bar for projects.
 *
 * @author Darrell Green, Jr. (DJ Green), Zarif Mazumder
 */
public final class ProjectSelectBar extends JPanel {
    public ProjectSelectBar() {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        displayProjects(Main.getProfileController().getProfile());
    }

    /**
     * Displays the projects of a profile.
     *
     * @author Darrell Green, Jr. (DJ), Zarif Mazumder
     * @param profile The profile whose projects will be displayed.
     */
    private void displayProjects(Profile profile) {
        // Create "+" button before adding project buttons
        JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open dialog to enter the new project name
                String newProjectName = JOptionPane.showInputDialog("Enter the name of the new Project");

                // Create new Project and add it to the profile
                if (newProjectName != null && !newProjectName.trim().isEmpty()) {
                    Project newProject = new Project();
                    newProject.setName(newProjectName);
                    profile.addProject(newProject);

                    // Add a new button with the name of the new Project at the left side
                    JButton newProjectButton = new JButton(newProject.getName());
                    ProjectSelectBar.this.add(newProjectButton, ProjectSelectBar.this.getComponentCount() - 1);

                    // redraw the select bar to show the newly added project
                    ProjectSelectBar.this.revalidate();
                    ProjectSelectBar.this.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Project name cannot be empty. Please try again.");
                }

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

