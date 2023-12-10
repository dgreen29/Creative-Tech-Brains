package app.models;

import app.controllers.ProfileController;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Handles profile import/export. Note: commas in user <code>String</code> input replaced with "%2C" on CSV write.
 * @author Zarif Mazumder
 */
public final class ProfileFactory {
    private static final String FILE_EXTENSION = ".txt";

    /**
     * Generates persistent database CSVs.
     * @author Zarif Mazumder
     * @throws IOException could not create database
     */
    public static void generateDB() throws IOException {
        File profileDB = new File("profile.csv");
        File projectDB = new File("project.csv");
        File detailDB = new File("detail.csv");
        File itemDB = new File("item.csv");
        File entryDB = new File("entry.csv");
        List<Boolean> writeableFiles = Arrays.asList(profileDB.createNewFile(), projectDB.createNewFile(), detailDB.createNewFile(),
                itemDB.createNewFile(), entryDB.createNewFile());
        if (writeableFiles.contains(false)) {
            throw new IOException("Could not create database.");
        }
    }

    /**
     * Saves app data to database. GUEST <code>Profile</code>s are skipped.
     * @param profileController query data
     * @throws IOException Writing file error
     */
    public static void writeToDB(ProfileController profileController) throws IOException {
        ArrayList<Profile> profiles = profileController.getProfiles();
        for (Profile profile : profiles) {
            String profileName = profile.getName();
            System.out.println(profileName);
            if (profileName.equals(Profile.DEFAULT_NAME)) continue;
            writeToProfileDB(profileName, profile.getEmail(), profile.getPrivilege());
            StringBuilder projectSB = new StringBuilder();
            for (Project project : profile.getProjects()) {
                String projectName = project.getName();
                projectSB.append(projectName);
                writeToDetailDB(projectName, project.getDetail().getText());
                LinkedList<Item> checklist = project.getChecklist();
                for (int i = 0; i < checklist.size(); i++) {
                    Item item = checklist.get(i);
                    writeToItemDB(projectName, i, item.getText());
                }
                LinkedList<Entry> entries = project.getBudget().getEntries();
                for (int i = 0; i < entries.size(); i++) {
                    Entry entry = entries.get(i);
                    writeToEntryDB(projectName, i, entry.getCost(), entry.getName(), entry.getQuantity());
                }
            }
            writeToProjectDB(profileName, projectSB.toString());
        }
    }

    /**
     * Writes <code>Profile</code> row to database
     * @param name <code>Profile</code> name
     * @param email already validated input
     * @param privilege <code>Privilege</code>
     * @throws IOException Writing file error
     */
    public static void writeToProfileDB(String name, String email, Profile.Privilege privilege) throws IOException {
        File profileDB = new File("project.csv");
        Scanner scanner = new Scanner(profileDB);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String nameForQuery = name.replaceAll(",", "%2C");
            if (Pattern.matches(nameForQuery, row)) {
                sb.append(nameForQuery)
                        .append(",")
                        .append(email)
                        .append(",")
                        .append(privilege)
                        .append(System.lineSeparator());
            }
        }
        FileWriter fW = new FileWriter(profileDB);
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    /**
     * Writes <code>Project</code> row to database
     * @param name <code>Profile</code> name
     * @param projects as comma-separated String
     * @throws IOException Writing file error
     */
    public static void writeToProjectDB(String name, String projects) throws IOException {
        File projectDB = new File("project.csv");
        Scanner scanner = new Scanner(projectDB);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String nameForQuery = name.replaceAll(",", "%2C");
            if (Pattern.matches(nameForQuery, row)) {
                sb.append(nameForQuery)
                        .append(",")
                        .append(projects)
                        .append(System.lineSeparator());
            }
        }
        FileWriter fW = new FileWriter(projectDB);
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    /**
     * Writes <code>Detail</code> row to database.
     * @param name <code>Project</code> name
     * @param text content of <code>Detail</code>
     * @throws IOException Writing file error
     */
    public static void writeToDetailDB(String name, String text) throws IOException {
        File detailDB = new File("detail.csv");
        Scanner scanner = new Scanner(detailDB);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String nameForQuery = name.replaceAll(",", "%2C");
            if (Pattern.matches(nameForQuery, row)) {
                sb.append(nameForQuery)
                        .append(",")
                        .append(text.replaceAll(",", "%2C"))
                        .append(System.lineSeparator());
            }
        }
        FileWriter fW = new FileWriter(detailDB);
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    /**
     * Writes <code>Item</code> row to database.
     * @param name <code>Project</code> name
     * @param index index
     * @param text <code>Item</code> text
     * @throws IOException Writing file error
     */
    public static void writeToItemDB(String name, Integer index, String text) throws IOException {
        File itemDB = new File("item.csv");
        Scanner scanner = new Scanner(itemDB);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String nameForQuery = name.replaceAll(",", "%2C");
            if (Pattern.matches(nameForQuery, row) && Pattern.matches(index.toString(), row)) {
                sb.append(nameForQuery)
                        .append(",")
                        .append(index)
                        .append(",")
                        .append(text.replaceAll(",", "%2C"))
                        .append(System.lineSeparator());
            }
        }
        FileWriter fW = new FileWriter(itemDB);
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    /**
     * Writes <code>Entry</code> row to database.
     * @param name <code>Project</code> name
     * @param index index
     * @param cost cost
     * @param entryName <code>Entry</code> name
     * @param quantity quantity
     * @throws IOException Writing file error
     */
    public static void writeToEntryDB(String name, Integer index, BigDecimal cost, String entryName, Integer quantity)
            throws IOException {
        File entryDB = new File("entry.csv");
        Scanner scanner = new Scanner(entryDB);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String nameForQuery = name.replaceAll(",", "%2C");
            if (Pattern.matches(nameForQuery, row) && Pattern.matches(index.toString(), row)) {
                sb.append(nameForQuery)
                        .append(",")
                        .append(index)
                        .append(",")
                        .append(cost.doubleValue())
                        .append(",")
                        .append(entryName.replaceAll(",", "%2C"))
                        .append(",")
                        .append(quantity)
                        .append(System.lineSeparator());
            }
        }
        FileWriter fW = new FileWriter(entryDB);
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    /**
     * Writes <code>Profile</code> to a <code>File</code> of the <code>Profile</code>'s name.
     * @author Zarif Mazumder
     * @param profile given <code>Profile</code>
     * @throws IOException Writing file error
     */
    public static void exportProfile(Profile profile) throws IOException {
        try (FileOutputStream fOut = new FileOutputStream(profile.getName() + FILE_EXTENSION, true)) {
            String singleLineProfile = profile.getName() + "," + profile.getEmail() + "," + profile.getPrivilege();
            fOut.write(singleLineProfile.getBytes());
        }
    }

    /**
     * Creates a <code>Profile</code> object from user OS file system input <code>File</code>.
     * @author Zarif Mazumder
     * @param data given <code>File</code>
     * @return resulting created <code>Profile</code>
     * @throws IOException Reading file error
     */
    public static Profile importProfile(File data) throws IOException {
        Scanner scanner = new Scanner(data);
        String[] values = scanner.nextLine().split(",");
        scanner.close();
        return new Profile(values[0], values[1], Profile.Privilege.valueOf(values[2]));
    }
}
