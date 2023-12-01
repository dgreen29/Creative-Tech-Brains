package app.models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/*
 * Author: Vindhriko Chandran Cain, Zarif Mazumder
 */

/**
 * Handles the writing of app data to a <code>File</code>.
 */
public final class ProfileWriter {

    /**
     * Writes <code>Profile</code> to a <code>File</code> of the <code>Profile</code>'s name.
     * @param profile given <code>Profile</code>
     * @throws IOException Writing file error
     */
    public static void exportProfile(Profile profile) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(profile.getName(), true);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(profile);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                System.out.println("Failed to close output stream.");
            }
        }
    }
}
