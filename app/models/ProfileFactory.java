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
    public static ArrayList<ProfileModel> readFromDB(File db) throws FileNotFoundException {
        ArrayList<ProfileModel> profiles = new ArrayList<>();
        Scanner scanner = new Scanner(db);
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            String profileName = values[0];
            String formattedName = values[0].replaceAll("\\b%2C\\b", ",");
            ProfileModel profileModel = new ProfileModel(formattedName, values[1], ProfileModel.Privilege.valueOf(values[2]));
            profileModel.setProjects(readFromProjectDB(profileName));
            profiles.add(profileModel);
        }
        scanner.close();
        return profiles;
    }

    /**
     * Read <code>Project</code> row from database.
     * This is a short-circuiting terminal operation.
     * @author Zarif Mazumder
     * @param profileName key = <code>Profile</code>'s name
     * @return <code>Profile</code>
     */
    public static ArrayList<Project> readFromProjectDB(String profileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("project.csv"));
        ArrayList<Project> projects = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(profileName)) {
                for (int i = 1; i < values.length; i++) {
                    String projectName = values[i];
                    Project.ProjectBuilder projectBuilder = new Project.ProjectBuilder(projectName);
                    readFromDetailDB(projectBuilder, projectName, profileName);
                    readFromItemDB(projectBuilder, projectName, profileName);
                    readFromEntryDB(projectBuilder, projectName, profileName);
                    projects.add(projectBuilder.build());
                }
                return projects;
            }
        }
        projects.add(new Project(ProfileModel.DEFAULT_PROJECT_NAME));
        return projects;
    }

    /**
     * Read <code>Detail</code> row from database.
     * This is a short-circuiting terminal operation.
     * @author Zarif Mazumder
     * @param projectBuilder out variable
     * @param projectName foreign key
     * @param profileName primary key
     */
    public static void readFromDetailDB(Project.ProjectBuilder projectBuilder, String projectName, String profileName)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("detail.csv"));
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(profileName) && values[1].equals(projectName)) {
                projectBuilder.setDetail(new DetailModel(values[2].replaceAll("\\b%2C\\b", ",")));
                return;
            }
        }
        projectBuilder.setDetail(new DetailModel());
    }

    /**
     * Read <code>Item</code> rows from database.
     * Item[] = {Profile:Name, Index, Text, isDone}
     * @author Zarif Mazumder
     * @param projectBuilder out variable
     * @param projectName foreign key
     * @param profileName primary key
     */
    public static void readFromItemDB(Project.ProjectBuilder projectBuilder, String projectName, String profileName)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("item.csv"));
        LinkedList<ItemModel> checklist = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(profileName) && values[1].equals(projectName)) {
                checklist.add(Integer.parseInt(values[2]), new ItemModel(
                        values[3].replaceAll("\\b%2C\\b", ","), Boolean.parseBoolean(values[4])));
            }
        }
        projectBuilder.setChecklist(checklist);
    }

    /**
     * Read <code>Entry</code> rows from database.
     * Entry[] = {Project:Name, Cost, Name, Quantity}
     * @author Zarif Mazumder
     * @param projectBuilder out variable
     * @param projectName foreign key
     * @param profileName primary key
     */
    public static void readFromEntryDB(Project.ProjectBuilder projectBuilder, String projectName, String profileName)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("entry.csv"));
        BudgetModel budgetModel = new BudgetModel();
        LinkedList<EntryModel> entries = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            if (values[0].equals(profileName) && values[1].equals(projectName)) {
                entries.add(Integer.parseInt(values[2]), new EntryModel(BigDecimal.valueOf(Double.parseDouble(values[3])),
                        values[4].replaceAll("\\b%2C\\b", ","), Integer.parseInt(values[5])));
            }
        }
        budgetModel.setEntries(entries);
        projectBuilder.setBudget(budgetModel);
    }

    /**
     * Saves app data to database. GUEST <code>Profile</code>s are skipped.
     * @author Zarif Mazumder
     * @param profileController query data
     * @throws IOException Writing file error
     */
    public static void writeToDB(ProfileController profileController) throws IOException {
        ArrayList<ProfileModel> profiles = profileController.getProfiles();
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
        for (ProfileModel profileModel : profiles) {
            String profileName = profileModel.getName().replaceAll(",", "%2C");
            if (profileName.equals(ProfileModel.DEFAULT_NAME)) continue;
            writeProfileRow(profileStringBuilder, profileName, profileModel.getEmail(), profileModel.getPrivilege());
            StringBuilder projectSB = new StringBuilder(profileName).append(",");
            for (Project project : profileModel.getProjects()) {
                String projectName = project.getName();
                projectSB.append(projectName).append(",");
                writeDetailRow(detailStringBuilder, profileName, projectName, project.getDetail().getText());
                LinkedList<ItemModel> checklist = project.getChecklist();
                for (int i = 0; i < checklist.size(); i++) {
                    ItemModel itemModel = checklist.get(i);
                    writeItemRow(itemStringBuilder, profileName, projectName, i, itemModel.getText(), itemModel.isDone());
                }
                LinkedList<EntryModel> entries = project.getBudget().getEntries();
                for (int i = 0; i < entries.size(); i++) {
                    EntryModel entryModel = entries.get(i);
                    writeEntryRow(entryStringBuilder, profileName, projectName, i, entryModel.getCost(), entryModel.getName(),
                            entryModel.getQuantity());
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
     * "Name, Email, Privilege"
     * @author Zarif Mazumder
     * @param sb out variable
     * @param name <code>Profile</code> name
     * @param email already validated input
     * @param privilege <code>Privilege</code>
     */
    public static void writeProfileRow(StringBuilder sb, String name, String email, ProfileModel.Privilege privilege) {
        sb.append(name)
                .append(",")
                .append(email)
                .append(",")
                .append(privilege)
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Detail</code> row.
     * "Name, Project, Text"
     * @author Zarif Mazumder
     * @param sb out variable
     * @param profileName primary key
     * @param projectName foreign key
     * @param text content of <code>Detail</code>
     */
    public static void writeDetailRow(StringBuilder sb, String profileName, String projectName, String text) {
        sb.append(profileName.replaceAll(",", "%2C"))
                .append(",")
                .append(projectName.replaceAll(",", "%2C"))
                .append(",")
                .append(text.replaceAll(",", "%2C"))
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Item</code> row.
     * "Profile, Project, Index, Text, isDone"
     * @author Zarif Mazumder
     * @param sb out variable
     * @param projectName <code>Project</code> name
     * @param index index
     * @param text <code>Item</code> text
     */
    public static void writeItemRow(StringBuilder sb, String profileName, String projectName, Integer index,
                                    String text, Boolean isDone) {
        sb.append(profileName.replaceAll(",", "%2C"))
                .append(",")
                .append(projectName.replaceAll(",", "%2C"))
                .append(",")
                .append(index)
                .append(",")
                .append(text.replaceAll(",", "%2C"))
                .append(",")
                .append(isDone.toString())
                .append(System.lineSeparator());
    }

    /**
     * Writes <code>Entry</code> row.
     * "Profile, Project, Index, Cost, Name, Quantity"
     * @author Zarif Mazumder
     * @param sb out variable
     * @param projectName <code>Project</code> name
     * @param index index
     * @param cost cost
     * @param entryName <code>Entry</code> name
     * @param quantity quantity
     */
    public static void writeEntryRow(StringBuilder sb, String profileName, String projectName, Integer index,
                                     BigDecimal cost, String entryName, Integer quantity) {
        sb.append(profileName.replaceAll(",", "%2C"))
                .append(",")
                .append(projectName.replaceAll(",", "%2C"))
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
     * @param profileModel given <code>Profile</code>
     * @throws IOException Writing file error
     */
    public static void exportProfile(ProfileModel profileModel) throws IOException {
        try (FileOutputStream fOut = new FileOutputStream(profileModel.getName() + FILE_EXTENSION, true)) {
            String singleLineProfile = profileModel.getName() + "," + profileModel.getEmail() + "," + profileModel.getPrivilege();
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
    public static ProfileModel importProfile(File data) throws IOException {
        Scanner scanner = new Scanner(data);
        String[] values = scanner.nextLine().split(",");
        scanner.close();
        return new ProfileModel(values[0], values[1], ProfileModel.Privilege.valueOf(values[2]));
    }
}
