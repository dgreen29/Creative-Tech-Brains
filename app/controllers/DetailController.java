package app.controllers;

import app.models.Project;

/**
 * Handles functionality and communication between <code>DetailView</code> and the <code>Detail</code> model.
 * @author Zarif Mazumder
 */
public class DetailController {
    /**
     * Reference to the app's project controller.
     */
    private final ProjectController projectController;

    public DetailController(ProjectController projectController) {
        this.projectController = projectController;
    }

    /**
     * @author Zarif Mazumder
     * @return text
     */
    public String getText() {
        return projectController.getProject().getDetail().getText();
    }

    /**
     * @author Zarif Mazumder
     * @param text text
     */
    public void setText(String text) {
        Project project = projectController.getProject();
        project.getDetail().setText(text);
    }
}
