package app.controllers;

import app.models.Profile;
import app.models.ProfileIO;
import app.models.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Handles functionality and communication between Views and the <code>Profile</code> model.
 * @author Zarif Mazumder
 */
public final class ProfileController {
    /**
     * Stores the list of profiles.
     */
    private final List<Profile> profiles = new ArrayList<>();
    /**
     * The current active profile.
     */
    private Profile currentProfile;
    /**
     * Reference to the app's project controller.
     */
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
        currentProfile = profile;
        return profile;
    }

    public void createProject(String name) {
        currentProfile.addProject(new Project(name));
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
     * Load profiles from database.
     * @param db csv
     */
    public void loadProfiles(File db) throws FileNotFoundException {
        Scanner scanner = new Scanner(db);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try (Scanner rowScanner = new Scanner(line)) {
                rowScanner.useDelimiter(",");
                while (rowScanner.hasNext()) {
                    System.out.print(scanner.next());
                }
            }
        }
    }

    /**
     * Takes <code>Proile</code> from given <code>File</code>.
     * @author Zarif Mazumder
     * @param data input <code>File</code>
     * @return true if <code>File</code> contains <code>Profile</code> object data.
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
     * @author Darrell Green, Jr., Zarif Mazumder
     * @return name
     */
    public String getName() {
        return currentProfile.getName();
    }

    /**
     * @author Darrell Green, Jr., Zarif Mazumder
     * @return email
     */
    public String getEmail() {
        return currentProfile.getEmail();
    }

    /**
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

    public enum invalidCredentials {
        NAME,
        EMAIL,
        IS_VALID
    }

    /**
     * Checks if <code>Profile</code> can be created using given data
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
     * @author Zarif Mazumder
     * @return <code>ProjectController</code>
     */
    public ProjectController getProjectController() {
        return projectController;
    }

    /**
     * @author Zarif Mazumder
     * Database Design:
     * Profile[] = {Name, Email, Privilege}
     * Project[] = {Profile:Name, 1:Name, n:Name}
     * Detail[] = {Profile:Name, Text}
     * Item[] = {Profile:Name, Text, Done}
     * Entry[] = {Profile:Name, Cost, Name, Quantity}
     */
    public List<Boolean> generateDB() throws IOException {
        return ProfileIO.generateDB();
    }
}
