package app.controllers;

import app.models.Team;
import app.models.Version;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 * 
 * @version 11.8.23
 * 
 * About controller class
 */
public final class AboutController {
	/*
	 * default constructor for AboutController class.
	 */
    public AboutController() {}

    /**
     * returns team.
     * @return Team.getTeam().
     */
    public String[] getTeam() {
        return Team.getTeam();
    }
    /**
     * returns version.
     * @return Version.getVersion().
     */
    public String getVersion() {
        return Version.getVersion();
    }
}
