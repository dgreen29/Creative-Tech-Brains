package app.controllers;

/**
 * Handles functionality and communication between
 * <code>DetailView</code> and the <code>Detail</code> model.
 * @author Zarif Mazumder
 */
public class DetailController {
    // Reference to the app's project controller.
    private final ProjectController projectController;

    /**
     * Handles functionality and communication between
     * DetailView and the Detail model.
     * @param projectController
     */
    public DetailController(ProjectController projectController) {
        this.projectController = projectController;
    }

    /**
     * Returns the text of the detail.
     * @author Zarif Mazumder
     * @return text
     */
    public String getText() {
        return projectController.getProject().getDetail().getText();
    }

    /**
     * Sets the text of the detail.
     * @author Zarif Mazumder
     * @param text text
     */
    public void setText(String text) {
        projectController.getProject().getDetail().setText(text);
    }
}
