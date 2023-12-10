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
     * Read app data from database.
     * @author Zarif Mazumder
     * @param db database
     * @return <code>ArrayList&lt;Profile&gt;</code>
     * @throws FileNotFoundException file not found or unreadable
     */
    public static ArrayList<Profile> readFromDB(File db) throws FileNotFoundException {
        ArrayList<Profile> profiles = new ArrayList<>();
        Scanner scanner = new Scanner(db);
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            Profile profile = new Profile(values[0].replaceAll("\\b%2C\\b", ","), values[1],
                    Profile.Privilege.valueOf(values[2]));
            profile.addProject(readFromProjectDB(profile, values[0]));
            profiles.add(profile);
        }
        scanner.close();
        return profiles;
    }

    /**
     * Read <code>Project</code> row from database.
     * This is a short-circuiting terminal operation.
     * @author Zarif Mazumder
     * @param profile out variable
     * @param name key = <code>Profile</code>'s name
     * @return <code>Profile</code>
     */
    public static Project readFromProjectDB(Profile profile, String name) {
        Scanner scanner = new Scanner("project.csv");
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(name)) {
                for (int i = 1; i < values.length; i++) {
                    String projectName = values[i];
                    Project.ProjectBuilder projectBuilder = new Project.ProjectBuilder(projectName);
                    readFromDetailDB(projectBuilder, projectName);
                    readFromItemDB(projectBuilder, projectName);
                    readFromEntryDB(projectBuilder, projectName);
                    return projectBuilder.build();
                }
            }
        }
        return new Project(Profile.DEFAULT_PROJECT_NAME);
    }

    /**
     * Read <code>Detail</code> row from database.
     * This is a short-circuiting terminal operation.
     * @author Zarif Mazumder
     * @param projectBuilder out variable
     * @param name <code>Project</code> name
     */
    public static void readFromDetailDB(Project.ProjectBuilder projectBuilder, String name) {
        Scanner scanner = new Scanner("detail.csv");
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(name)) {
                projectBuilder.setDetail(new Detail(values[1].replaceAll("\\b%2C\\b", ",")));
                return;
            }
        }
        projectBuilder.setDetail(new Detail());
    }

    /**
     * Read <code>Item</code> rows from database.
     * Item[] = {Profile:Name, Text, Done}
     * @author Zarif Mazumder
     * @param projectBuilder out variable
     * @param name <code>Project</code> name
     */
    public static void readFromItemDB(Project.ProjectBuilder projectBuilder, String name) {
        Scanner scanner = new Scanner("item.csv");
        LinkedList<Item> checklist = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(name)) {
                checklist.add(new Item(values[1].replaceAll("\\b%2C\\b", ",")));
            }
        }
        projectBuilder.setChecklist(checklist);
    }

    /**
     * Read <code>Entry</code> rows from database.
     * Entry[] = {Project:Name, Cost, Name, Quantity}
     * @author Zarif Mazumder
     * @param projectBuilder out variable
     * @param name <code>Project</code> name
     */
    public static void readFromEntryDB(Project.ProjectBuilder projectBuilder, String name) {
        Scanner scanner = new Scanner("entry.csv");
        Budget budget = new Budget();
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(name)) {
                budget.addEntry(new Entry(new BigDecimal(values[1]),
                        values[2].replaceAll("\\b%2C\\b", ","), Integer.parseInt(values[3])));
            }
        }
        projectBuilder.setBudget(budget);
    }

    /**
     * Saves app data to database. GUEST <code>Profile</code>s are skipped.
     * @author Zarif Mazumder
     * @param profileController query data
     * @throws IOException Writing file error
     */
    public static void writeToDB(ProfileController profileController) throws IOException {
        ArrayList<Profile> profiles = profileController.getProfiles();
        for (Profile profile : profiles) {
            String profileName = profile.getName();
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
     * @author Zarif Mazumder
     * @param name <code>Profile</code> name
     * @param email already validated input
     * @param privilege <code>Privilege</code>
     * @throws IOException Writing file error
     */
    public static void writeToProfileDB(String name, String email, Profile.Privilege privilege) throws IOException {
        File profileDB = new File("profile.csv");
        Scanner scanner = new Scanner(profileDB);
        StringBuilder sb = new StringBuilder();
        String formattedName = name.replaceAll(",", "%2C");
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (Pattern.matches(formattedName, row)) { // Replace row
                appendProfileRow(sb, formattedName, email, privilege);
            } else {
                sb.append(row);
            }
        }
        if (sb.isEmpty()) { // Append row
            appendProfileRow(sb, formattedName, email, privilege);
        }
        FileWriter fW = new FileWriter(profileDB);
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    private static void appendProfileRow(StringBuilder stringBuilder, String name, String email,
                                         Profile.Privilege privilege) {
        stringBuilder.append(name)
                .append(",")
                .append(email)
                .append(",")
                .append(privilege)
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Project</code> row to database
     * @author Zarif Mazumder
     * @param name <code>Profile</code> name
     * @param projects as comma-separated String
     * @throws IOException Writing file error
     */
    public static void writeToProjectDB(String name, String projects) throws IOException {
        File projectDB = new File("project.csv");
        Scanner scanner = new Scanner(projectDB);
        StringBuilder sb = new StringBuilder();
        String formattedName = name.replaceAll(",", "%2C");
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (Pattern.matches(formattedName, row)) { // Replace row
                appendProjectRow(sb, formattedName, projects);
            } else {
                sb.append(row);
            }
        }
        FileWriter fW = new FileWriter(projectDB);
        if (sb.isEmpty()) { // Append row
            appendProjectRow(sb, formattedName, projects);
        }
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    private static void appendProjectRow(StringBuilder stringBuilder, String name, String projects) {
        stringBuilder.append(name)
                .append(",")
                .append(projects)
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Detail</code> row to database.
     * @author Zarif Mazumder
     * @param name <code>Project</code> name
     * @param text content of <code>Detail</code>
     * @throws IOException Writing file error
     */
    public static void writeToDetailDB(String name, String text) throws IOException {
        File detailDB = new File("detail.csv");
        Scanner scanner = new Scanner(detailDB);
        StringBuilder sb = new StringBuilder();
        String formattedName = name.replaceAll(",", "%2C");
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (Pattern.matches(formattedName, row)) { // Replace row
                appendDetailRow(sb, formattedName, text);
            }
        }
        FileWriter fW = new FileWriter(detailDB);
        if (sb.isEmpty()) { // Append row
            appendDetailRow(sb, formattedName, text);
        }
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    private static void appendDetailRow(StringBuilder stringBuilder, String name, String text) {
        stringBuilder.append(name)
                .append(",")
                .append(text.replaceAll(",", "%2C"))
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Item</code> row to database.
     * @author Zarif Mazumder
     * @param name <code>Project</code> name
     * @param index index
     * @param text <code>Item</code> text
     * @throws IOException Writing file error
     */
    public static void writeToItemDB(String name, Integer index, String text) throws IOException {
        File itemDB = new File("item.csv");
        Scanner scanner = new Scanner(itemDB);
        StringBuilder sb = new StringBuilder();
        String formattedName = name.replaceAll(",", "%2C");
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (Pattern.matches(formattedName, row) && Pattern.matches(index.toString(), row)) { // Replace row
                appendItemRow(sb, formattedName, index, text);
            }
        }
        FileWriter fW = new FileWriter(itemDB);
        if (sb.isEmpty()) { // Append row
            appendItemRow(sb, formattedName, index, text);
        }
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    /**
     * @author Zarif Mazumder
     * @param stringBuilder
     * @param name
     * @param index
     * @param text
     */
    private static void appendItemRow(StringBuilder stringBuilder, String name, Integer index, String text) {
        stringBuilder.append(name)
                .append(",")
                .append(index)
                .append(",")
                .append(text.replaceAll(",", "%2C"))
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Entry</code> row to database.
     * @author Zarif Mazumder
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
        String formattedName = name.replaceAll(",", "%2C");
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (Pattern.matches(formattedName, row) && Pattern.matches(index.toString(), row)) { // Replace row
                appendEntryRow(sb, index, cost, entryName, quantity, name);
            }
        }
        FileWriter fW = new FileWriter(entryDB);
        if (sb.isEmpty()) { // Append row
            appendEntryRow(sb, index, cost, entryName, quantity, name);
        }
        fW.append(sb.toString());
        scanner.close();
        fW.flush();
    }

    private static void appendEntryRow(StringBuilder stringBuilder, Integer index, BigDecimal cost, String entryName,
                                       Integer quantity, String name) {
        stringBuilder.append(name)
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
     * @param data <code>File</code>
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
