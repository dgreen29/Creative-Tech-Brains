package app.models;

/**
 * Stores project team names.
 * @author Darrell Green, Jr., Harman Singh, Vindhriko Chandran Cain, Zarif Mazumder
 */
public final class About {
    private static final String[] TEAM = {"Darrell Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
            "Zarif Mazumder"};
    private final static String VERSION = "1.1.0";

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
