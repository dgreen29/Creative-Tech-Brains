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
     * @param data
     * @return resulting created Profile
     * @throws IOException
     */
    public static Profile createProfile(File data) throws IOException {
        ObjectInputStream ois = null;
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(data);
            ois = new ObjectInputStream(fin);
            return (Profile) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (ois != null) {
                ois.close();
                fin.close();
            }
        }
    }
}
