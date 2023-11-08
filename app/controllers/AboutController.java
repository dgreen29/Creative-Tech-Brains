package app.controllers;

import app.models.Team;

public final class AboutController {
    public AboutController() {}

    public String[] getTeam() {
        return Team.getTeam();
    }
}