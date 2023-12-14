package app.controllers;

import app.models.Profile;
import app.models.ProfileIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Handles functionality and communication between Views
 * and the <code>Profile</code> model.
 * @author Zarif Mazumder
 */
public final class ProfileController {
    // TODO: Add functionality to create new profiles.
    private final List<Profile> profiles = new ArrayList<>();

    private Profile currentProfile;
    private final ProjectController projectController;

    /**
     * Controller class that handles the functionality related to
     * profile management.
     * This class contains methods to create a profile, manage the
     * current profile, and interact with the project controller.
     * @param projectController
     */
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
        currentProfile = profile;
        return profile;
    }

    /**
     * Writes current profile to a file.
     * @author Zarif Mazumder
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
     * @author Zarif Mazumder
     * @param data input <code>File</code>
     * @return true if <code>File</code> contains
     * <code>Profile</code> object data.
     */
    public boolean importProfile(File data) {
        if (data == null) {
            return false;
        }
        try {
            currentProfile = ProfileIO.importProfile(data);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * @author Zarif Mazumder
     * @return <code>Profile</code>
     */
    public Profile getProfile() {
        return currentProfile;
    }

    /**
     * Returns the name of the current profile.
     * @author Darrell Green, Jr., Zarif Mazumder
     * @return name
     */
    public String getName() {
        return currentProfile.getName();
    }

    /**
     * Returns the email of the current profile.
     * @author Darrell Green, Jr., Zarif Mazumder
     * @return email
     */
    public String getEmail() {
        return currentProfile.getEmail();
    }

    /**
     * Returns the privilege of the current profile.
     * @author Zarif Mazumder
     * @return privilege
     */
    public String getPrivilege() {
        return currentProfile.getPrivilege().toString();
    }

    /**
     * Sets current <code>Profile</code>.
     * @author Zarif Mazumder
     * @param profile given <code>Profile</code>
     * @return did set
     */
    public boolean setCurrentProfile(Profile profile) {
        currentProfile = profile;
        return true;
    }

    /**
     * Represents the possible invalid credentials when validating
     * a profile.
     */
    public enum invalidCredentials {
        NAME,
        EMAIL,
        IS_VALID
    }

    /**
     * Checks if <code>Profile</code> can be created using given
     * data.
     * @author Darrell Green, Jr., Zarif Mazumder
     */
    public ArrayList<invalidCredentials> validateProfile(String name, String email) {
        ArrayList<invalidCredentials> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add(invalidCredentials.NAME);
        }
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (email == null || email.isEmpty() || !Pattern.matches(regexPattern, email)) {
            /* https://www.rfc-editor.org/info/rfc5322 */
            errors.add(invalidCredentials.EMAIL);
        }
        if (errors.isEmpty()) {
            errors.add(invalidCredentials.IS_VALID);
        }
        return errors;
    }

    /**
     * Returns the list of profiles.
     * @author Zarif Mazumder
     * @return <code>ProjectController</code>
     */
    public ProjectController getProjectController() {
        return projectController;
    }
}
