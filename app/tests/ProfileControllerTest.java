package app.tests;

import app.controllers.ProfileController;
import app.models.Profile;
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
     */
    @Test
    public void createProfile() {
        Profile profile = new Profile("Test", "test@example.com");
        assertEquals(testController.createProfile("Test", "test@example.com"), profile);
    }

    /**
     * Tests the getter for the profile.
     */
    @Test
    public void getProfile() {
        assertEquals(testController.getProfile(), new Profile("GUEST", "(no email address)"));
    }

    /**
     * Tests importing an invalid profile.
     */
    @Test
    public void importInvalidProfile() {
        assertFalse(testController.importProfile(null));
    }
}
