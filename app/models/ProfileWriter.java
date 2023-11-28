package app.models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class ProfileWriter {
    public static void exportProfile(Profile profile) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(profile.getName(), true);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(profile);
        } finally {
            if (oos != null) {
                oos.close();
                fout.close();
            }
        }
    }
}
