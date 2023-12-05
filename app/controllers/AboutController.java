package app.controllers;

import app.models.About;

/*
 * Author: Zarif Mazumder
 */

/**
 * Passes information from <code>About</code> model to the view.
 */
public final class AboutController {

    public String[] getTeam() {
        return About.getTeam();
    }

    public String getVersion() {
        return About.getVersion();
    }
}