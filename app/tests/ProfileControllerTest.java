package app.tests;

import app.controllers.ProfileController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProfileControllerTest {
    private ProfileController testController;

    @BeforeEach
    public void Setup() {
        testController = new ProfileController();
    }

    @Test
    public void NameTest() {
        assertEquals("Steve", testController.getName());
    }

    @Test
    public void EmailTest() {
        assertEquals("example@example.org", testController.getEmail());
    }
}
