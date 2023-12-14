package app.tests;

import app.controllers.ProfileController;
import app.models.ProfileModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Profile Controller.
 * @author Harman Singh, Zarif Mazumder
 */
public class ProfileControllerTest {
    /**
     * The Profile Controller used in testing.
     */
    private ProfileController testController;

    /**
     * Setup method that instantiates new controllers before each test.
     */
    @BeforeEach
    public void Setup() {
        testController = new ProfileController();
    }

    /**
     * Tests the creation of a new profile.
     * @author Zarif Mazumder
     */
    @Test
    public void createProfile() {
        ProfileModel profileModel = new ProfileModel("Test", "test@example.com");
        assertEquals(testController.createProfile("Test", "test@example.com"), profileModel);
    }

    /**
     * Tests the getter for the profile.
     * @author Zarif Mazumder
     */
    @Test
    public void getProfile() {
        assertEquals(testController.getProfile(), new ProfileModel("GUEST", "(no email address)"));
    }

    /**
     * Tests the getter for the list of profiles.
     * @author Harman Singh
     */
    @Test
    public void getAllProfiles() {
        testController.createProfile("test", "test@test.com");
        testController.createProfile("test 2", "test2@test.com");
        assertEquals(3, testController.getProfiles().size());
    }

    /**
     * Tests exporting the current profile.
     * @author Harman Singh
     */
    @Test
    public void exportProfileTest(){
        assertTrue(testController.exportProfile());
    }

    /**
     * Tests the getter for the current profile's name.
     */
    @Test
    public void getNameTest() {
        assertEquals("GUEST", testController.getName());
    }

    /**
     * Tests the getter for the current profile's email.
     */
    @Test
    public void getEmailTest() {
        assertEquals("(no email address)", testController.getEmail());
    }

    /**
     * Tests the getter for the current profile's privilege.
     */
    @Test
    public void getPrivilegeTest() {
        assertEquals(ProfileModel.Privilege.USER, testController.getPrivilege());
    }

    /**
     * Tests the setter for the current profile's privilege.
     */
    @Test
    public void setPrivilegeTest() {
        testController.setPrivilege(ProfileModel.Privilege.ADMIN);
        assertEquals(ProfileModel.Privilege.ADMIN, testController.getPrivilege());
    }

    /**
     * Tests importing an invalid profile.
     */
    @Test
    public void importInvalidProfile() {
        assertFalse(testController.importProfile(null));
    }
}
