package app.models;

import java.io.*;

/**
 * Handles profile import/export from given data.
 *
 * @author Zarif Mazumder
 */
public final class ProfileIO {
    private static final String FILE_EXTENSION = ".txt"; // File extension for profile files.

    /**
     * Exports a profile to a file.
     *
     * @param profile The profile to export.
     *
     * @throws IOException If there is an error while exporting the
     * profile.
     */
    public static void exportProfile(Profile profile) throws IOException {
        try (FileOutputStream fOut = new FileOutputStream(profile.getName() + FILE_EXTENSION, true);
             ObjectOutputStream oos = new ObjectOutputStream(fOut)) {
            oos.writeObject(profile);
        }
    }

    /**
     * Creates a <code>Profile</code> object from user OS file system input <code>File</code>.
     * @author Zarif Mazumder
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
