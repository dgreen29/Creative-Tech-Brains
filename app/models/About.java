package app.models;

/*
 * Authors: Darrell Green, Jr., Harman Singh, Vindhriko Chandran Cain, Zarif Mazumder
 */

/**
 * Stores project team names.
 */
public final class About {
    private static final String[] TEAM = {"Darrell Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
            "Zarif Mazumder"};
    private final static String VERSION = "1.1.0";

    public static String[] getTeam() {
        return TEAM;
    }

    public static String getVersion() {
        return VERSION;
    }
}
