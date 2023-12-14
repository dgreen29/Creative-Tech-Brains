package app.tests;

import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
public class ProjectControllerTest {
    private ProjectController testController;

    @BeforeEach
    public void setup() {testController = new ProjectController(new ProfileController());}

    @Test
    public void test() {
    }
}
