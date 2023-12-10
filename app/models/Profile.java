package app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
     * Represents a user profile.
     *The Profile class stores information about a user, such as their
     * name, email address, privilege level, and projects. It provides
     * methods to access and modify the profile's properties.
     *The class is serializable and can be used to persist profile data.
 */
public final class Profile implements Serializable {
    private static final String DEFAULT_NAME = "GUEST"; // Default name.

    private static final String DEFAULT_EMAIL = "(no email address)"; // Default email address.

    private final String name; // Name of the user.

    private final String email; // Email address of the user.

    private transient Privilege privilege; // Privilege of the user.

    private final List<Project> projects; // List of projects.

   /**
        * Creates a profile with the given name and email.
        * If the name or email is empty, a default value will be used.
        * The profile will also have a list of projects, with an initial project added.
        * @param name The name of the profile.
        * @param email The email address of the profile.
        */
    public Profile(String name, String email) {
        this.name = name.isEmpty() ? DEFAULT_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
        privilege = Privilege.USER;
        projects = new ArrayList<>();
        projects.add(new Project());
    }

    /**
         * Creates a profile with the given name, email, and privilege.
         * If the name or email is empty, a default value will be used.
         * The profile will also have a list of projects, with an initial
        * project added.
         * @param name The name of the profile.
         * @param email The email address of the profile.
         * @param privilege The privilege level of the profile.
     */
    public Profile(String name, String email, Privilege privilege) {
        this.name = name.isEmpty() ? DEFAULT_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
        this.privilege = privilege;
        projects = new ArrayList<>();
        projects.add(new Project());
    }

    /**
         * Creates a profile with the given name, email, privilege, and
        * projects.
         * If the name or email is empty, a default value will be used.
         * @param name The name of the profile.
         * @param email The email address of the profile.
         * @param privilege The privilege level of the profile.
         * @param projects The list of projects of the profile.
     */
    public Profile(String name, String email, Privilege privilege, ArrayList<Project> projects) {
        this.name = name.isEmpty() ? DEFAULT_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
        this.privilege = privilege;
        this.projects = projects;
    }

    /**
     * Retrieves the name of the profile.
     * @author Zarif Mazumder
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the email address of the profile.
     * @author
     * @return The email address of the profile.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the list of projects of the profile.
     * @author Zarif Mazumder
     * @return list of projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Creates a GUEST account if empty <code>String</code>
     * @author Zarif Mazumder
     * @return Name of <code>Profile</code>
     */
    @Override
    public String toString() {
        return name;
    }

    /**
         * Compares this Profile object with the specified object for
        * equality.
         * @param o The object to compare with this Profile.
         * @return true if the specified object is equal to this Profile,
        * false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Profile profile)) {
            return false;
        }
        return (this.name.equals(profile.getName()) && this.email.equals(profile.getEmail()));
    }

    /**
     * Adds a project to the list of projects of the profile.
     * @param newProject The project to add.
     */
    public void addProject(Project newProject) {
    }

    /**
     * Represents a privilege level.
     * The Privilege enum represents the privilege level of a user.
     * The privilege level determines what actions a user can
     * perform.
     * The enum is serializable and can be used to persist profile
     * data.
     */
    public enum Privilege {
        USER,
        ADMIN
    }

    /**
     * Retrieves the privilege level of the profile.
     *
     * @return The privilege level of the profile.
     */
    public Privilege getPrivilege() {
        return privilege;
    }

    /**
     * Sets the privilege level of the profile.
     *
     * @param privilege The privilege level of the profile.
     */
    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}