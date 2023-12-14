package app.tests;

import app.controllers.ProfileController;
import app.models.ProfileModel;
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
        ProfileModel profileModel = new ProfileModel("Test", "test@example.com");
        assertEquals(testController.createProfile("Test", "test@example.com"), profileModel);
    }

    @Test
    public void getProfile() {
        assertEquals(testController.getProfile(), new ProfileModel("GUEST", "(no email address)"));
    }

    @Test
    public void importInvalidProfile() {
        assertFalse(testController.importProfile(null));
    }
}
