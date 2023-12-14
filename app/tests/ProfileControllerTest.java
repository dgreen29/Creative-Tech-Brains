package app.tests;

import app.controllers.ProfileController;
import app.models.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the ProfileController class.
 * @author Harman Singh, Zarif Mazumder
 */
public class ProfileControllerTest {
    private ProfileController testController; // Reference to the ProfileController.

    @BeforeEach
    /**
     *
     * Sets up the ProfileController.
     */
    public void Setup() {
        testController = new ProfileController();
    }

    @Test
    /**
     * Tests the createProfile method.
     */
    public void createProfile() {
        Profile profile = new Profile("Test", "test@example.com");
        assertEquals(testController.createProfile("Test", "test@example.com"), profile);
    }

    @Test
    /**
     * Tests the getProfile method.
     */
    public void getProfile() {
        assertEquals(testController.getProfile(), new Profile("GUEST", "(no email address)"));
    }

    @Test
    /**
     * Tests the importProfile method.
     */
    public void importInvalidProfile() {
        assertFalse(testController.importProfile(null));
    }
}
