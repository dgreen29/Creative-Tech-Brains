package app.models;

import java.io.*;

/*
 * Author: Zarif Mazumder
 */

/**
 * Handles profile import/export from given data.
 */
public final class ProfileIO {

    /**
     * Writes <code>Profile</code> to a <code>File</code> of the <code>Profile</code>'s name.
     * @param profile given <code>Profile</code>
     * @throws IOException Writing file error
     */
    public static void exportProfile(Profile profile) throws IOException {
        try (FileOutputStream fOut = new FileOutputStream(profile.getName(), true);
             ObjectOutputStream oos = new ObjectOutputStream(fOut)) {
            oos.writeObject(profile);
        }
    }

    /**
     * Creates a <code>Profile</code> object from user OS file system input <code>File</code>.
     * @param data given <code>File</code>
     * @return resulting created <code>Profile</code>
     * @throws IOException Reading file error
     */
    public static Profile importProfile(File data) throws IOException, ClassNotFoundException {
        try (FileInputStream fIn = new FileInputStream(data); ObjectInputStream ois = new ObjectInputStream(fIn)) {
            return (Profile) ois.readObject();
        }
    }
}
