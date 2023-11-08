package app.controllers;

import app.models.Team;
import app.models.Version;

public final class AboutController {
    public AboutController() {}

    public String[] getTeam() {
        return Team.getTeam();
    }

    public int getVersion() {
        return Version.getVersion();
    }
}