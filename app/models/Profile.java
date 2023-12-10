package app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores a user's app data.
 * @author Zarif Mazumder
 */
public final class Profile {
    /**
     * Constant String storing the name of the default profile.
     */
    public static final String DEFAULT_NAME = "GUEST";
    /**
     * Constant String containing the email address for the default profile.
     */
    private static final String DEFAULT_EMAIL = "(no email address)";
    private static final String DEFAULT_PROJECT_NAME = "Project 1";
    /**
     * String storing the profile's name.
     */
    private final String name;
    /**
     * String storing the profile's email address.
     */
    private final String email;
    /**
     * Enum for storing the profile's privilege level.
     */
    private Privilege privilege;
    /**
     * List of projects belonging to the profile.
     */
    private final List<Project> projects;

    /**
     * Creates a GUEST account if empty <code>String</code> parameters given.
     * @param name Name
     * @param email Email Address
     */
    public Profile(String name, String email) {
        this.name = name.isEmpty() ? DEFAULT_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
        privilege = Privilege.USER;
        projects = new ArrayList<>();
        projects.add(new Project(DEFAULT_PROJECT_NAME));
    }

    public Profile(String name, String email, Privilege privilege) {
        this.name = name.isEmpty() ? DEFAULT_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
        this.privilege = privilege;
        projects = new ArrayList<>();
        projects.add(new Project(DEFAULT_PROJECT_NAME));
    }

    public Profile(String name, String email, Privilege privilege, ArrayList<Project> projects) {
        this.name = name.isEmpty() ? DEFAULT_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
        this.privilege = privilege;
        this.projects = projects;
    }

    /**
     * @author Zarif Mazumder
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @author Zarif Mazumder
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @author Zarif Mazumder
     * @return list of projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * @author Zarif Mazumder
     * @return Name of <code>Profile</code>
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @author Zarif Mazumder
     * @param o <code>Object</code> instance of <code>Profile</code>
     * @return true if equal in name and email
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Profile profile)) {
            return false;
        }
        return (this.name.equals(profile.getName()) && this.email.equals(profile.getEmail()));
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    /**
     * Facilitates purview of <code>Profile</code>
     * @author Zarif Mazumder
     */
    public enum Privilege {
        USER,
        ADMIN
    }

    /**
     * @author Zarif Mazumder
     * @return <code>Privilege</code>
     */
    public Privilege getPrivilege() {
        return privilege;
    }

    /**
     * @author Zarif Mazumder
     * @param privilege <code>Privilege</code>
     */
    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}