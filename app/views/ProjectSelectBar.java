package app.views;

import app.Main;
import app.models.Profile;
import app.models.Project;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Displays all projects that can be switched to.
 * @author Zarif Mazumder
 */
public final class ProjectSelectBar extends JPanel {
    public ProjectSelectBar() {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        displayProjects(Main.getProfileController().getProfile());
    }

    private void displayProjects(Profile profile) {
        for (Project project : profile.getProjects()) {
            this.add(new JButton(project.getName()));
        }
        this.add(new JButton("+"));
    }
}
