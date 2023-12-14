package app.tests;
import app.controllers.BudgetController;
import app.controllers.DetailController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.Detail;
import app.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
public class DetailControllerTest {
    private final String TEST_DETAIL_STRING = "Test Details";
    private DetailController testController;

    @BeforeEach
    public void setup() {testController = new DetailController(new ProjectController(new ProfileController()));}

    @Test
    public void defaultDetailsTest() {
        assertEquals(testController.getText(), Detail.DEFAULT_TEXT);
    }

    @Test
    public void setDetailTest() {
        testController.setText(TEST_DETAIL_STRING);
        assertEquals(TEST_DETAIL_STRING, testController.getText());
    }

}
