package app.controllers;

import app.models.Item;
import app.models.Project;

import java.util.LinkedList;

/**
 * Handles functionality and communication between
 * <code>Project</code> model and various models and
 * controllers.
 * @author Zarif Mazumder
 */
public class ProjectController {
    // Reference to the app's project controller.
    private Project currentProject;
    // Reference to the app's profile controller.
    private final ProfileController profileController;

    /**
     * Handles functionality and communication between
     * Project model and various models and controllers.
     */
    public ProjectController(ProfileController profileController) {
        this.profileController = profileController;
        currentProject = profileController.getProfile().getProjects().get(0);
    }

    /**
     * Returns the current <code>Project</code> in the current
     * <code>Profile</code>.
     * @author Zarif Mazumder
     * @return current <code>Project</code>
     */
    public Project getProject() {
        return currentProject;
    }

    /**
     * Sets the current <code>Project</code>.
     * @author Zarif Mazumder
     * @param index of projects in the current <code>Profile</code>
     */
    public void setCurrentProject(int index) {
        currentProject = profileController.getProfile().getProjects().get(index);
    }

    /**
     * Returns the list of <code>Project</code>s in the current
     * <code>Profile</code>.
     * @author Zarif Mazumder
     * @return checklist
     */
    public LinkedList<Item> getChecklist() {
        return currentProject.getChecklist();
    }

    /**
     * Adds <code>Item</code> to the checklist.
     * @author Zarif Mazumder
     * @param text content of <code>Item</code>
     */
    public void addItem(String text) {
        currentProject.addItem(text);
    }

    /**
     * Modifies given <code>Item</code> in the checklist.
     * @author Zarif Mazumder
     * @param index location of <code>Entry</code>
     * @param text text
     */
    public void setItem(int index, String text) {
        currentProject.setItem(index, text);
    }

    /**
     * Removes <code>Item</code> from the checklist.
     * @author Zarif Mazumder
     * @param index location of <code>Item</code>
     */
    public void removeItem(int index) {
        currentProject.removeItem(index);
    }
}
