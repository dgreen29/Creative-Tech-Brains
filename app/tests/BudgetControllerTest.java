package app.tests;

import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.EntryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Budget Controller.
 * @author Harman Singh
 */
public class BudgetControllerTest {
    /**
     * The controller used in testing.
     */
    private BudgetController testController;


    /**
     * Instantiates new controllers before each test.
     * @author Harman Singh
     */
    @BeforeEach
    public void setup() {
        testController = new BudgetController(new ProjectController(new ProfileController()));
    }

    /**
     * Tests adding an Entry to the budget.
     * @author Harman Singh
     */
    @Test
    public void testAdd() {
        EntryModel testEntry = new EntryModel(new BigDecimal(269), "Test", 1);
        testController.addEntry(new BigDecimal(269), "Test", 1);

        assertEquals(testEntry, testController.getEntries().getFirst());
    }

    /**
     * Tests setting an Entry into the budget.
     * @author Harman Singh
     */
    @Test
    public void testSet1() {
        EntryModel testEntry1 = new EntryModel(new BigDecimal(75), "Test Entry 1", 4);
        EntryModel testEntry2 = new EntryModel(new BigDecimal(40), "Test Entry 2", 2);
        EntryModel testEntry3 = new EntryModel(new BigDecimal(23), "Test Entry 3", 1);

        testController.addEntry(testEntry1);
        testController.addEntry(testEntry2);
        testController.setEntry(1, testEntry3);

        assertEquals(testEntry3, testController.getEntries().get(1));
    }

    /**
     * Tests setting an Entry into the budget.
     * @author Harman Singh
     */
    @Test
    public void testSet2() {
        EntryModel testEntry = new EntryModel(new BigDecimal(22), "Test",1);
        EntryModel repEntry = new EntryModel(new BigDecimal(22), "Replacement", 2);
        testController.addEntry(testEntry);
        testController.setEntry(0, repEntry.getCost(), repEntry.getName(), repEntry.getQuantity());

        assertEquals(repEntry, testController.getEntries().getFirst());
    }

    /**
     * Tests removing an Entry from the budget.
     * @author Harman Singh
     */
    @Test
    public void testRemove() {
        EntryModel testEntry = new EntryModel(new BigDecimal(2), "Test", 1);
        testController.addEntry(testEntry);
        testController.removeEntry(0);


        assertEquals(0, testController.getEntries().size());
    }

}
