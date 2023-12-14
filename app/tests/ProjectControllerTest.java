package app.tests;

import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.Entry;
import app.models.Item;
import app.models.Profile;
import app.models.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ProjectControllerTest {
    private ProjectController testController;
    private ProfileController testPFController;

    @BeforeEach
    public void setup() {
        testPFController = new ProfileController();
        testController = new ProjectController(testPFController);
    }

    @Test
    public void testSetCurrentProject1() {
        Project testProj = new Project("Test Project");
        testController.setCurrentProject(testProj);
        assertEquals(testProj, testController.getProject());
    }

    @Test
    public void testSetCurrentProject2() {
        ArrayList<Project> tests = testProjectGenerator(2);
        testPFController.getProfile().getProjects().add(tests.get(0));
        testPFController.getProfile().getProjects().add(tests.get(1));


        testController.setCurrentProject(2);
        System.out.println(testController.getProject().getName());
        assertEquals(tests.get(1), testController.getProject());
    }

    @Test
    public void checkListTestEmpty() {
        assertEquals("[]", testController.getProject().getChecklist().toString(), "Checklist is not empty.");
    }

    @Test
    public void checkListAddTest() {
        Item testItem = new Item("Test Item");
        testController.getProject().addItem(testItem.getText());
        assertEquals(testItem.getText(), testController.getProject().getChecklist().get(0).getText());
    }

    @Test
    public void checkListSetTest1() {
        Item[] testItems = {new Item("Test 1"), new Item("Test 2")};
        testController.getProject().addItem(testItems[0].getText());
        testController.getProject().setItem(0, testItems[1].getText(), false);

        assertEquals(testItems[1].getText(), testController.getChecklist().get(0).getText(), "Checklist Item was not set properly");
    }

    @Test
    public void checkListRemoveTest() {
        Item[] testItems = {new Item("Test 1"), new Item("Test 2")};
        testController.getProject().addItem(testItems[0].getText());
        testController.getProject().addItem(testItems[1].getText());

        testController.getProject().removeItem(0);

        assertEquals(testItems[1].getText(), testController.getProject().getChecklist().get(0).getText());

    }


    private ArrayList<Project> testProjectGenerator(int size) {
        ArrayList<Project> testList = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            testList.add(new Project("Test Project " + (i + 1)));
        }
        return testList;
    }


}
