package app.controllers;

import app.models.AboutModel;

/**
 * Passes information from <code>About</code> model to the view.
 * @author Vindhriko Chandran Cain, Zarif Mazumder
 */
public final class AboutController {

    /**
     * @author Vindhriko Chandran Cain, Zarif Mazumder
     * @return String[] of team members
     */
    public String[] getTeam() {
        return AboutModel.getTeam();
    }

    /**
     * @author Vindhriko Chandran Cain, Zarif Mazumder
     * @return program version
     */
    public String getVersion() {
        return AboutModel.getVersion();
    }
}