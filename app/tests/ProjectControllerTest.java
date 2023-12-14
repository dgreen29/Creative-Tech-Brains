package app.tests;

import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.Item;
import app.models.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Project Controller.
 * @author Harman Singh
 */
public class ProjectControllerTest {
    /**
     * The Project Controller used in testing.
     */
    private ProjectController testController;
    /**
     * The Profile Controller used in testing. It is needed to instantiate the test Project Controller.
     * It is also need to manipulate the test projects for a test profile.
     */
    private ProfileController testPFController;

    /**
     * Setup method that instantiates new controllers before each test.
     * @author Harman Singh
     */
    @BeforeEach
    public void setup() {
        testPFController = new ProfileController();
        testController = new ProjectController(testPFController);
    }

    /**
     * Tests the overloaded setter using a Project object for the current project.
     * @author Harman Singh
     */
    @Test
    public void testSetCurrentProject1() {
        Project testProj = new Project("Test Project");
        testController.setCurrentProject(testProj);
        assertEquals(testProj, testController.getProject());
    }

    /**
     * Tests the overloaded setter using an index for the current project.
     * @author Harman Singh
     */
    @Test
    public void testSetCurrentProject2() {
        Project[] testProjects = {new Project("Test Project 1"), new Project("Test Project 2")};
        testPFController.getProfile().getProjects().add(testProjects[0]);
        testPFController.getProfile().getProjects().add(testProjects[1]);

        testController.setCurrentProject(2);
        assertEquals(testProjects[1], testController.getProject());
    }

    /**
     * Tests the default state of the checklist upon instantiation.
     * @author Harman Singh
     */
    @Test
    public void checkListTestEmpty() {
        assertEquals("[]", testController.getProject().getChecklist().toString(), "Checklist is not empty.");
    }

    /**
     * Tests adding an item to the checklist.
     * @author Harman Singh
     */
    @Test
    public void checkListAddTest() {
        Item testItem = new Item("Test Item");
        testController.getProject().addItem(testItem.getText());
        assertEquals(testItem.getText(), testController.getProject().getChecklist().get(0).getText());
    }

    /**
     * Tests the setter for the checklist item.
     * @author Harman Singh
     */
    @Test
    public void checkListSetTest1() {
        Item[] testItems = {new Item("Test 1"), new Item("Test 2")};
        testController.getProject().addItem(testItems[0].getText());
        testController.getProject().setItem(0, testItems[1].getText(), false);

        assertEquals(testItems[1].getText(), testController.getChecklist().get(0).getText(), "Checklist Item was not set properly");
    }

    /**
     * Tests the remove method for a checklist item.
     * @author Harman Singh
     */
    @Test
    public void checkListRemoveTest() {
        Item[] testItems = {new Item("Test 1"), new Item("Test 2")};
        testController.getProject().addItem(testItems[0].getText());
        testController.getProject().addItem(testItems[1].getText());

        testController.getProject().removeItem(0);

        assertEquals(testItems[1].getText(), testController.getProject().getChecklist().get(0).getText());

    }

}
