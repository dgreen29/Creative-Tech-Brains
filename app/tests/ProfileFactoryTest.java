package app.tests;

import app.controllers.ProfileController;
import app.models.ProfileFactory;
import app.models.ProfileModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ProfileFactory
 * @author Harman Singh
 */
public class ProfileFactoryTest {
    /**
     * The profile controller used in testing to manipulate a profile and ensure changes are reflected in the database.
     */
    private ProfileController testPFC;

    /**
     * Instantiates new profile controller for each test.
     */
    @BeforeEach
    public void setup() {
        testPFC = new ProfileController();
    }

    /**
     * Checks whether the database is empty in the default state.
     */
    @Test
    public void dBFileEmptyTest() {
        try {
            ProfileFactory.writeToDB(testPFC);
        } catch (IOException e) {
            fail("Error creating database!", e);
        }
        assertTrue(checkFile(new File("detail.csv")), "Details.csv does not exist");
        assertTrue(checkFile(new File("project.csv")), "Project.csv does not exist");
        assertTrue(checkFile(new File("profile.csv")), "Profile.csv does not exist");
        assertTrue(checkFile(new File("item.csv")), "Item.csv does not exist");
        assertTrue(checkFile(new File("entry.csv")), "Entry.csv does not exist");
    }

    /**
     * Checks whether changes to a profile are reflected in the database when exported.
     */
    @Test
    public void DBContentTest() {
        ArrayList<ProfileModel> readTest;
        testPFC.createProfile("Test PF", "tester1@test.com");
        testPFC.createProject("test project");
        testPFC.getProjectController().getProject().getDetail().setText("This is a test project!");
        testPFC.getProjectController().getProject().addItem("Test Item");

        ProfileModel testProfile = testPFC.getProfile();

        try {
            ProfileFactory.writeToDB(testPFC);
        } catch (IOException e) {
            fail("Error Creating Database");
        }
        try{
            readTest = ProfileFactory.readFromDB(new File("profile.csv"));

            //Checking data read back from database
            assertEquals(testProfile.getName(), readTest.get(0).getName());
            assertEquals(testProfile.getEmail(), readTest.get(0).getEmail());
            assertEquals(testProfile.getProjects().get(1).getName(), readTest.get(0).getProjects().get(1).getName());
            assertEquals(testProfile.getProjects().get(1).getDetail().getText(), readTest.get(0).getProjects().get(1).getDetail().getText());

        } catch (FileNotFoundException e) {
            fail("Database file not found");
        }


    }

    /**
     * Exports a test profile to a file and reads it back in.
     */
    @Test
    public void profileImportExportTest() {
        testPFC.createProfile("Test PF 2", "tester2@test.com");
        testPFC.exportProfile();
        testPFC.getProfiles().removeAll(testPFC.getProfiles());//remove all profiles in controller.
        testPFC.importProfile(new File("Test PF 2.txt"));
        assertEquals("Test PF 2", testPFC.getProfile().getName());
        assertEquals("tester2@test.com", testPFC.getProfile().getEmail());
    }

    /**
     * Helper method that check if a file is empty, if it exists.
     * @param file the file to check.
     * @return true if file is empty, false if not or file DNE.
     */
    private boolean checkFile(File file) {
        if (!file.exists()) {
            return false;
        }
        return file.length() == 0;
    }
}
