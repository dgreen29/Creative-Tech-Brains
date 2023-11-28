package app.models;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 *
 * @version 11.26.23
 *
 *
 */

import java.io.Serializable;

/**
 * The Profile class stores a user's app data.
 */
public final class Profile implements Serializable {
    private static final String DEFAULT_PROFILE_NAME = "GUEST";
    private static final String DEFAULT_EMAIL = "(no email address)";
    private String name;
    private String email;

    public Profile(String name, String email) {
        this.name = name.isEmpty() ? DEFAULT_PROFILE_NAME : name;
        this.email = email.isEmpty() ? DEFAULT_EMAIL : email;
    }

    /**
     * Method purpose: This getter method is meant to return a
     * specific name value in the form of a String.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method purpose: This getter method is meant to return a
     * specific email value in the form of a String.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name;
    }
}