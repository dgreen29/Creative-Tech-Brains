package app.controllers;

import app.Main;
import app.models.Profile;
import app.models.ProfileFactory;
import app.models.Project;
import app.views.ProjectsView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Handles functionality and communication between Views and the <code>Profile</code> model.
 * @author Zarif Mazumder
 */
public final class ProfileController {
    /**
     * Stores the list of profiles.
     */
    private ArrayList<Profile> profiles = new ArrayList<>();
    /**
     * The current active profile.
     */
    private Profile currentProfile;
    /**
     * Reference to the app's project controller.
     */
    private ProjectController projectController;

    public ProfileController() {
        currentProfile = createProfile("", "");
        projectController = new ProjectController(this);
    }

    /**
     * @author Zarif Mazumder
     * @return profiles
     */
    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    /**
     * Creates an instance of a <code>Profile</code>
     * @param name - Name
     * @param email - Email Address
     * @return created <code>Profile</code>
     */
    public Profile createProfile(String name, String email) {
        Profile profile = new Profile(name, email);
        if (currentProfile != null && currentProfile.getName().equals("GUEST")) {
            ArrayList<Project> projects = new ArrayList<>();
            projects.add(currentProfile.getProjects().get(0));
            profile.setProjects(projects); // Save data generated as GUEST
        }
        currentProfile = profile;
        projectController = new ProjectController(this);
        profiles.add(profile);
        return profile;
    }

    public void createProject(String name) {
        Project project = new Project(name);
        currentProfile.addProject(project);
        projectController.setCurrentProject(project);
    }

    /**
     * Writes current profile to a file.
     * @author Zarif Mazumder
     * @return true if <code>File</code> is written
     */
    public boolean exportProfile() {
        try {
            ProfileFactory.exportProfile(currentProfile);
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
        profiles = ProfileFactory.readFromDB(db);
        if (!profiles.isEmpty()) {
            currentProfile = profiles.get(0);
            projectController.setCurrentProject(0);
        }
        Main.setCurrentView(new ProjectsView(this));
    }

    /**
     * Takes <code>Profile</code> from given <code>File</code>.
     * @author Zarif Mazumder
     * @param data input <code>File</code>
     * @return true if <code>File</code> contains <code>Profile</code> object data.
     */
    public boolean importProfile(File data) {
        if (data == null) {
            return false;
        }
        try {
            currentProfile = ProfileFactory.importProfile(data);
            profiles.add(currentProfile);
            return true;
        } catch (IOException e) {
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
    public Profile.Privilege getPrivilege() {
        return currentProfile.getPrivilege();
    }

    /**
     * @author Zarif Mazumder
     * @param privilege USER, ADMIN
     */
    public void setPrivilege(Profile.Privilege privilege) {
        currentProfile.setPrivilege(privilege);
    }

    /**
     * Sets current <code>Profile</code>.
     * @author Zarif Mazumder
     * @param profile given <code>Profile</code>
     */
    public void setCurrentProfile(Profile profile) {
        currentProfile = profile;
        projectController = new ProjectController(this);
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
     * Detail[] = {Profile:Name, Project:Name, Text}
     * Item[] = {Profile:Name, Project:Name, Text, Done}
     * Entry[] = {Profile:Name, Project:Name, Cost, Name, Quantity}
     */
    public void generateDB() throws IOException {
        ProfileFactory.generateDB();
    }
}
