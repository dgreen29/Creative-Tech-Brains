package app.tests;

import app.controllers.AboutController;
import app.models.AboutModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the About Controller.
 * @author Harman Singh
 */
class AboutControllerTest {
    /**
     * The controller used in testing.
     */
    private AboutController testController;

    /**
     * Instantiates new controllers before each test.
     * @author Harman Singh
     */
    @BeforeEach
    public void setup() {
        testController = new AboutController();
    }

    /**
     * Tests the getter for the team member names.
     * @author Harman Singh
     */
    @Test
    public void teamTest() {
        assertArrayEquals(new String[]{"Darrell Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
                "Zarif Mazumder"}, testController.getTeam());
    }
    /**
     * Tests the getter for the version number.
     * @author Harman Singh
     */
    @Test
    public void versionTest() {
        assertEquals("1.2.0", testController.getVersion());
    }
}