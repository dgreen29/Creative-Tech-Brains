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
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                System.out.println("Failed to close output stream.");
            }
        }
    }
}
