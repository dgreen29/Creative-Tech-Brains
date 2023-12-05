package app.controllers;

import app.models.Profile;
import app.models.ProfileIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Zarif Mazumder
 */

/**
 * Handles functionality and communication between Views and the <code>Profile</code> model.
 */
public final class ProfileController {
    private final List<Profile> profiles = new ArrayList<>();
    private Profile currentProfile;
    private final ProjectController projectController;

    public ProfileController() {
        currentProfile = createProfile("", "");
        projectController = new ProjectController(this);
    }

    /**
     * Creates an instance of a <code>Profile</code>
     * @param name - Name
     * @param email - Email Address
     * @return created <code>Profile</code>
     */
    public Profile createProfile(String name, String email) {
        Profile profile = new Profile(name, email);
        profiles.add(profile);
        currentProfile = profile;
        return profile;
    }

    /**
     *
     * @return true if <code>File</code> is written
     */
    public boolean exportProfile() {
        try {
            ProfileIO.exportProfile(currentProfile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Takes <code>Proile</code> from given <code>File</code>.
     * @param data input <code>File</code>
     * @return true if <code>File</code> contains <code>Profile</code> object data.
     */
    public boolean importProfile(File data) {
        if (data == null) {
            return false;
        }
        try {
            currentProfile = ProfileIO.importProfile(data);
            profiles.add(currentProfile);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public Profile getProfile() {
        return currentProfile;
    }

    public String getName() {
        return currentProfile.getName();
    }

    public String getEmail() {
        return currentProfile.getEmail();
    }

    /**
     * Sets valid current <code>Profile</code>.
     * @param profile given <code>Profile</code>
     * @return did set
     */
    public boolean setCurrentProfile(Profile profile) {
        if (!validateProfile(profile)) {
            return false;
        }
        currentProfile = profile;
        return true;
    }

    /**
     * Checks if given <code>Profile</code> is valid. The stored list must already contain it.
     * @param profile given <code>Profile</code>
     * @return result of contains
     */
    public boolean validateProfile(Profile profile) {
        return profiles.contains(profile);
    }

    public ProjectController getProjectController() {
        return projectController;
    }
}
