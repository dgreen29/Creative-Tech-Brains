package app.tests;

import app.controllers.AboutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the AboutController class.
 * @author Harman Singh
 */
class AboutControllerTest {
    private AboutController testController; // Reference to the AboutController.

    @BeforeEach
    /**
     * Sets up the AboutController.
     * @throws Exception
     * @postcondition testController != null
     */
    public void setup() {
        testController = new AboutController();
    }

    @Test
    /**
     * Tests the getTeam method.
     *
     */
    public void teamTest() {
        assertArrayEquals(new String[]{"Darrell Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
                "Zarif Mazumder"}, testController.getTeam());
    }
}