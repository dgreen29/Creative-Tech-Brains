package app.models;
/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 * 
 * @version 11.8.23
 * 
 * Version class
 */
public final class Version {
  
    private final static String  VERSION = "1.0.0";

    /**
     * returns Version number.
     * @return VERSION
     */
    public static String getVersion() {
        return VERSION;
    }
}
