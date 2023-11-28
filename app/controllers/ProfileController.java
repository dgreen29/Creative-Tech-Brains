package app.controllers;

import app.models.Profile;
import app.models.ProfileReader;
import app.models.ProfileWriter;

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
    private static final String DEFAULT_PROFILE_NAME = "GUEST";
    private static final String DEFAULT_EMAIL = "(no email address)";
    private List<Profile> profiles = new ArrayList<>();
    private Profile currentProfile;

    public ProfileController() {
        currentProfile = createProfile(DEFAULT_PROFILE_NAME, DEFAULT_EMAIL);
    }

    /**
     * Creates an instance of a <code>Profile</code>
     * @param name - Name
     * @param email - Email Address
     * @return
     */
    public Profile createProfile(String name, String email) {
        Profile profile = new Profile(name, email);
        profiles.add(profile);
        currentProfile = profile;
        return profile;
    }

    /**
     *
     * @return true if File is written
     */
    public boolean exportProfile() {
        try {
            ProfileWriter.exportProfile(currentProfile);
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
            currentProfile = ProfileReader.createProfile(data);
            profiles.add(currentProfile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Returns the current <code>Profile</code>. Creates default <code>Profile</code> if none exist.
     * @return currentProfile
     */
    public Profile getProfile() {
        return currentProfile != null ? currentProfile : createProfile(DEFAULT_PROFILE_NAME, DEFAULT_EMAIL);
    }

    public String getName() {
        return currentProfile.getName();
    }

    public String getEmail() {
        return currentProfile.getEmail();
    }

    public boolean setCurrentProfile(Profile profile) {
        if (!validateProfile(profile)) {
            return false;
        }
        currentProfile = profile;
        return true;
    }

    /**
     * Checks if given <code>Profile</code> is valid. Profiles list must already contain it.
     * @param profile given Profile
     * @return result of contains
     */
    public boolean validateProfile(Profile profile) {
        return profiles.contains(profile);
    }
}
