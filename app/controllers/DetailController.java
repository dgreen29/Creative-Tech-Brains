package app.controllers;

/*
 * Author: Zarif Mazumder
 */

/**
 * Handles functionality and communication between <code>DetailView</code> and the <code>Detail</code> model.
 */
public class DetailController {
    private final ProjectController projectController;

    public DetailController(ProjectController projectController) {
        this.projectController = projectController;
    }

    public String getText() {
        return projectController.getProject().getDetail().getText();
    }

    public void setText(String text) {
        projectController.getProject().getDetail().setText(text);
    }
}
