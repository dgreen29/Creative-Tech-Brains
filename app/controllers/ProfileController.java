package app.controllers;

import app.models.Profile;

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
	// profile controller constructor.
    public ProfileController() {}

    /**
     * gets name.
     * @return Profile.getName().
     */
    public String getName() {
        return Profile.getName();
    }
    /**
     * gets email.
     * @return Profile.getEmail().
     */
    public String getEmail() {
        return Profile.getEmail();
    }
}
