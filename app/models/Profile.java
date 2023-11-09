package app.models;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 *
 * @version 11.8.23
 *
 * Program Purpose: The purpose of this program is to satisfy the
 * Project UI and About Screen requirements laid out in the project
 * description as referenced by the Client Interview.
 */

/**
 * Class Purpose: The Profile class is part of the models package
 * and is used to store a user's information.
 */
public final class Profile {

    /*
    Declaring a private static String variable named name and
    initializing it with the value "Steve". This variable is also
    declared as final. This value is meant to hold a user's name.
     */
    private static final String name = "Steve";

    /*
    Declaring a private static String variable named email and
    initializing it with the value "Steve". This variable is also
    declared as final. This value is meant to hold a user's email
    address.
     */
    private static final String email = "example@example.org";

    /**
     * Method purpose: This getter method is meant to return a
     * specific name value in the form of a String.
     * @return name
     */
    public static String getName() {
        return name;
    }
    /**
     * Method purpose: This getter method is meant to return a
     * specific email value in the form of a String.
     * @return email
     */public static String getEmail() {
        return email;
    }
}