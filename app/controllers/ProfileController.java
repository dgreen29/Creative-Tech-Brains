package app.controllers;

import app.models.Profile;
import app.models.ProfileReader;
import app.models.ProfileWriter;

import java.io.File;
import java.io.IOException;
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
        currentProfile = createProfile(DEFAULT_PROFILE_NAME, DEFAULT_EMAIL);
    }

    public Profile createProfile(String name, String email) {
        Profile profile = new Profile(name, email);
        profiles.add(profile);
        return profile;
    }

    public boolean exportProfile() {
        try {
            ProfileWriter.exportProfile(currentProfile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

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

    public boolean validateProfile(Profile profile) {
        return profiles.contains(profile);
    }
}
