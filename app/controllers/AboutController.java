package app.controllers;

import app.models.Team;
import app.models.Version;

public final class AboutController {
    public AboutController() {}

    public String[] getTeam() {
        return Team.getTeam();
    }

    public String getVersion() {
        return Version.getVersion();
    }
}