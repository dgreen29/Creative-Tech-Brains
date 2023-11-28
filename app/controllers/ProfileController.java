package app.controllers;

import app.models.Profile;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 * 
 * @version 11.8.23
 * 
 * Profile controller class
 */
public final class ProfileController {
    private static final String DEFAULT_PROFILE_NAME = "GUEST";
    private static final String DEFAULT_EMAIL = "(no email address)";
    private List<Profile> profiles = new ArrayList<>();
    private Profile currentProfile;

    public ProfileController() {
        currentProfile = getProfile();
    }

    public Profile createProfile() {
        Profile profile = new Profile("","");
        profiles.add(profile);
        return profile;
    }

    public boolean exportProfile() {
        return true;
    }

    public boolean importProfile() {
        return true;
    }

    public Profile getProfile() {
        return new Profile(DEFAULT_PROFILE_NAME, DEFAULT_EMAIL);
    }

    /**
     * gets name.
     * @return .
     */
    public String getName() {
        return currentProfile.getName();
    }

    /**
     * gets email.
     * @return .
     */
    public String getEmail() {
        return currentProfile.getEmail();
    }

    public Profile setCurrentProfile(Profile profile) {
        currentProfile = profile;
        return currentProfile;
    }
}
