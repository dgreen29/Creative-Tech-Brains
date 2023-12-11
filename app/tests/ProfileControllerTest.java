package app.tests;

import app.controllers.ProfileController;
import app.models.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Harman Singh, Zarif Mazumder
 */
public class ProfileControllerTest {
    private ProfileController testController;

    @BeforeEach
    public void Setup() {
        testController = new ProfileController();
    }

    @Test
    public void createProfile() {
        Profile profile = new Profile("Test", "test@example.com");
        assertEquals(testController.createProfile("Test", "test@example.com"), profile);
    }

    @Test
    public void getProfile() {
        assertEquals(testController.getProfile(), new Profile("GUEST", "(no email address)"));
    }

    @Test
    public void importInvalidProfile() {
        assertFalse(testController.importProfile(null));
    }
}
