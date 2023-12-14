package app.controllers;

import java.util.Arrays;
import java.util.List;

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
     * Returns a list of log titles.
     * @author Darrell Green, Jr. (DJ Green)
     * @return a list of log titles
     */
    public static List<String> getLogsTitles() {
        return Arrays.asList(new String[0]);
    }

    /**
     * Selects a log with the given title.
     * @author Darrell Green, Jr. (DJ Green)
     * @param selectedLogTitle the title of the log to be selected
     */
    public static void selectLog(String selectedLogTitle) {

    }

    /**
     * Returns the title of the currently selected log.
     * @author Darrell Green, Jr. (DJ Green)
     * @return the title of the currently selected log
     */
    public static String getCurrentLogTitle() {
        return null;
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

    /**
     * Returns an array of log titles.
     * @author Darrell Green, Jr. (DJ Green)
     * @return an array of log titles
     */
    public String[] getLogTitles() {

        return new String[0];
    }

    /**
     * Sets the title of the log.
     * @author Darrell Green, Jr. (DJ Green)
     * @param text the text of the title
     */
    public void setLogTitle(String text) {

    }
}