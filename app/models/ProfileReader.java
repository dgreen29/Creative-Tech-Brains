package app.models;

import java.io.*;

/*
 * Author: Zarif Mazumder
 */

/**
 * Handles profile creation from given data.
 */
public final class ProfileReader {

    /**
     * Creates a <code>Profile</code> object from user OS file system input <code>File</code>.
     * @param data given <code>File</code>
     * @return resulting created <code>Profile</code>
     * @throws IOException Reading file error
     */
    public static Profile createProfile(File data) throws IOException, ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream(data); ObjectInputStream ois = new ObjectInputStream(fin)) {
            return (Profile) ois.readObject();
        }
    }
}
