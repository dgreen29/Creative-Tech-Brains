package app.models;

import app.controllers.ProfileController;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Handles profile import/export.
 * Note: commas in user <code>String</code> input replaced with "%2C" on CSV write.
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
            String formattedName = values[0].replaceAll("\\b%2C\\b", ",");
            Profile profile = new Profile(formattedName, values[1], Profile.Privilege.valueOf(values[2]));
            profile.setProjects(readFromProjectDB(values[0]));
            profiles.add(profile);
        }
        scanner.close();
        return profiles;
    }

    /**
     * Read <code>Project</code> row from database.
     * This is a short-circuiting terminal operation.
     * @author Zarif Mazumder
     * @param name key = <code>Profile</code>'s name
     * @return <code>Profile</code>
     */
    public static ArrayList<Project> readFromProjectDB(String name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("project.csv"));
        ArrayList<Project> projects = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(name)) {
                for (int i = 1; i < values.length; i++) {
                    String projectName = values[i];
                    Project.ProjectBuilder projectBuilder = new Project.ProjectBuilder(projectName);
                    readFromDetailDB(projectBuilder, projectName);
                    readFromItemDB(projectBuilder, projectName);
                    readFromEntryDB(projectBuilder, projectName);
                    projects.add(projectBuilder.build());
                }
                return projects;
            }
        }
        projects.add(new Project(Profile.DEFAULT_PROJECT_NAME));
        return projects;
    }

    /**
     * Read <code>Detail</code> row from database.
     * This is a short-circuiting terminal operation.
     * @author Zarif Mazumder
     * @param projectBuilder out variable
     * @param name <code>Project</code> name
     */
    public static void readFromDetailDB(Project.ProjectBuilder projectBuilder, String name)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("detail.csv"));
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
    public static void readFromItemDB(Project.ProjectBuilder projectBuilder, String name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("item.csv"));
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
    public static void readFromEntryDB(Project.ProjectBuilder projectBuilder, String name)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("entry.csv"));
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
        FileWriter profileFileWriter = new FileWriter("profile.csv");
        FileWriter projectFileWriter = new FileWriter("project.csv");
        FileWriter detailFileWriter = new FileWriter("detail.csv");
        FileWriter itemFileWriter = new FileWriter("item.csv");
        FileWriter entryFileWriter = new FileWriter("entry.csv");
        StringBuilder profileStringBuilder = new StringBuilder();
        StringBuilder projectStringBuilder = new StringBuilder();
        StringBuilder detailStringBuilder = new StringBuilder();
        StringBuilder itemStringBuilder = new StringBuilder();
        StringBuilder entryStringBuilder = new StringBuilder();
        for (Profile profile : profiles) {
            String profileName = profile.getName().replaceAll(",", "%2C");
            if (profileName.equals(Profile.DEFAULT_NAME)) continue;
            writeProfileRow(profileStringBuilder, profileName, profile.getEmail(), profile.getPrivilege());
            StringBuilder projectSB = new StringBuilder(profileName).append(",");
            for (Project project : profile.getProjects()) {
                String projectName = project.getName();
                projectSB.append(projectName).append(",");
                writeDetailRow(detailStringBuilder, projectName, project.getDetail().getText());
                LinkedList<Item> checklist = project.getChecklist();
                for (int i = 0; i < checklist.size(); i++) {
                    Item item = checklist.get(i);
                    writeItemRow(itemStringBuilder, projectName, i, item.getText());
                }
                LinkedList<Entry> entries = project.getBudget().getEntries();
                for (int i = 0; i < entries.size(); i++) {
                    Entry entry = entries.get(i);
                    writeEntryRow(entryStringBuilder, projectName, i, entry.getCost(), entry.getName(), entry.getQuantity());
                }
            }
            projectSB.deleteCharAt(projectSB.length() - 1);
            projectStringBuilder.append(projectSB).append(System.lineSeparator());
        }
        profileFileWriter.append(profileStringBuilder);
        profileFileWriter.flush();
        projectFileWriter.append(projectStringBuilder);
        projectFileWriter.flush();
        detailFileWriter.append(detailStringBuilder);
        detailFileWriter.flush();
        itemFileWriter.append(itemStringBuilder);
        itemFileWriter.flush();
        entryFileWriter.append(entryStringBuilder);
        entryFileWriter.flush();
    }

    /**
     * Writes <code>Profile</code> row.
     * @author Zarif Mazumder
     * @param sb out variable
     * @param name <code>Profile</code> name
     * @param email already validated input
     * @param privilege <code>Privilege</code>
     */
    public static void writeProfileRow(StringBuilder sb, String name, String email, Profile.Privilege privilege) {
        sb.append(name)
                .append(",")
                .append(email)
                .append(",")
                .append(privilege)
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Detail</code> row.
     * @author Zarif Mazumder
     * @param sb out variable
     * @param name <code>Project</code> name
     * @param text content of <code>Detail</code>
     */
    public static void writeDetailRow(StringBuilder sb, String name, String text) {
        sb.append(name.replaceAll(",", "%2C"))
                .append(",")
                .append(text.replaceAll(",", "%2C"))
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Item</code> row.
     * @author Zarif Mazumder
     * @param sb out variable
     * @param name <code>Project</code> name
     * @param index index
     * @param text <code>Item</code> text
     */
    public static void writeItemRow(StringBuilder sb, String name, Integer index, String text) {
        sb.append(name.replaceAll(",", "%2C"))
                .append(",")
                .append(index)
                .append(",")
                .append(text.replaceAll(",", "%2C"))
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Entry</code> row.
     * @author Zarif Mazumder
     * @param sb out variable
     * @param name <code>Project</code> name
     * @param index index
     * @param cost cost
     * @param entryName <code>Entry</code> name
     * @param quantity quantity
     */
    public static void writeEntryRow(StringBuilder sb, String name, Integer index, BigDecimal cost, String entryName,
                                     Integer quantity) {
        sb.append(name.replaceAll(",", "%2C"))
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