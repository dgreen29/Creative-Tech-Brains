package app.controllers;

import app.models.Team;
import app.models.Version;

/*
 * Author: Zarif Mazumder
 */

/**
 * Passes information from <code>Team</code> and <code>Version</code> models to the view.
 */
public final class AboutController {

    public String[] getTeam() {
        return Team.getTeam();
    }

    public String getVersion() {
        return Version.getVersion();
    }
}