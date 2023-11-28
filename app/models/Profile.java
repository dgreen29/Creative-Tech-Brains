package app.models;

import java.io.Serializable;

/*
 * Author: Zarif Mazumder
 */

/**
 * The <code>Profile</code> class stores a user's app data.
 */
public final class Profile implements Serializable {
    private static final String DEFAULT_PROFILE_NAME = "GUEST";
    private static final String DEFAULT_EMAIL = "(no email address)";
    private String name;
    private String email;

    /**
     * Creates a GUEST account if empty <code>String</code> parameters given.
     * @param name Name
     * @param email Email Address
     */
    public Profile(String name, String email) {
        this.name = name.isEmpty() ? DEFAULT_PROFILE_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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
        return (profile.name.equals(this.name) && profile.email.equals(this.email));
    }
}