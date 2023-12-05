package app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Zarif Mazumder
 */

/**
 * Stores a user's app data.
 */
public final class Profile implements Serializable {
    private static final String DEFAULT_NAME = "GUEST";
    private static final String DEFAULT_EMAIL = "(no email address)";
    private final String name;
    private final String email;
    private Privilege privilege;
    private final List<Project> projects;

    /**
     * Creates a GUEST account if empty <code>String</code> parameters given.
     * @param name Name
     * @param email Email Address
     */
    public Profile(String name, String email) {
        this.name = name.isEmpty() ? DEFAULT_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
        privilege = Privilege.GUEST;
        projects = new ArrayList<>();
        projects.add(new Project());
    }

    // TODO: loadProfile

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    /**
     * @return Name of <code>Profile</code>
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Profile profile)) {
            return false;
        }
        return (this.name.equals(profile.getName()) && this.email.equals(profile.getEmail()));
    }

    public enum Privilege {
        GUEST,
        ADMIN
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}