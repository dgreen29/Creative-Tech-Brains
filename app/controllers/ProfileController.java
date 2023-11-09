package app.controllers;

import app.models.Profile;

public final class ProfileController {
    public ProfileController() {}

    public String getName() {
        return Profile.getName();
    }

    public String getEmail() {
        return Profile.getEmail();
    }
}