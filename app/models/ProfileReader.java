package app.models;

import java.io.*;

public final class ProfileReader {

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
