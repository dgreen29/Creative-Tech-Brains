package app.controllers;

import app.models.Item;
import app.models.Project;

import java.util.LinkedList;

/*
 * Author: Zarif Mazumder
 */

/**
 * Handles functionality and communication between <code>Project</code> model and various models and controllers.
 */
public class ProjectController {
    private Project currentProject;
    private final ProfileController profileController;

    public ProjectController(ProfileController profileController) {
        this.profileController = profileController;
        currentProject = profileController.getProfile().getProjects().get(0);
    }

    public Project getProject() {
        return currentProject;
    }

    /**
     * Sets the current <code>Project</code>.
     * @param index of projects in the current <code>Profile</code>
     */
    public void setCurrentProject(int index) {
        currentProject = profileController.getProfile().getProjects().get(index);
    }

    public LinkedList<Item> getChecklist() {
        return currentProject.getChecklist();
    }

    /**
     * Adds <code>Item</code> to the checklist.
     * @param text content of <code>Item</code>
     */
    public void addItem(String text) {
        currentProject.addItem(text);
    }

    public void setItem(int index, String text) {
        currentProject.setItem(index, text);
    }

    /**
     * Removes <code>Item</code> from the checklist.
     * @param index location of <code>Item</code>
     */
    public void removeItem(int index) {
        currentProject.removeItem(index);
    }
}
