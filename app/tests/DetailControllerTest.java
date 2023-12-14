package app.tests;

import app.controllers.DetailController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.DetailModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Detail Controller.
 * @author Harman Singh
 */
public class DetailControllerTest {
    /**
     * The controller used in testing.
     */
    private DetailController testController;

    /**
     * Instantiates new controllers before each test.
     * @author Harman Singh
     */
    @BeforeEach
    public void setup() {testController = new DetailController(new ProjectController(new ProfileController()));}

    /**
     * Tests the default state of the detail.
     * @author Harman Singh
     */
    @Test
    public void defaultDetailsTest() {
        assertEquals(testController.getText(), DetailModel.DEFAULT_TEXT);
    }

    /**
     * Tests the setter for the details.
     * @author Harman Singh
     */
    @Test
    public void setDetailTest() {
        String TEST_DETAIL_STRING = "Test Details";
        testController.setText(TEST_DETAIL_STRING);
        assertEquals(TEST_DETAIL_STRING, testController.getText());
    }

}
