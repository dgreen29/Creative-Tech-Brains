package app.models;

public final class Profile {
    private static final String name = "Steve";
    private static final String email = "example@example.org";

    public static String getName() {
        return name;
    }
    public static String getEmail() {
        return email;
    }
}