package app.models;

/**
 * Stores project team names.
 * @author Darrell Green, Jr., Harman Singh, Vindhriko Chandran Cain, Zarif Mazumder
 */
public final class About {
    /**
     * Array of Strings containing team member's name at each index.
     */
    private static final String[] TEAM = {"Darrell Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
            "Zarif Mazumder"};
    /**
     * String storing the current version number of the app.
     */
    private final static String VERSION = "1.2.0";

    /**
     * @author Vindhriko Chandran Cain, Zarif Mazumder
     * @return String[] of team names
     */
    public static String[] getTeam() {
        return TEAM;
    }

    /**
     * @author Zarif Mazumder
     * @return program version
     */
    public static String getVersion() {
        return VERSION;
    }
}
