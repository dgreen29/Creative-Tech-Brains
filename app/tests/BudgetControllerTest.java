package app.tests;

import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Harman Singh
 */
public class BudgetControllerTest {
    private BudgetController testController;


    @BeforeEach
    public void setup() {
        testController = new BudgetController(new ProjectController(new ProfileController()));
    }

    @Test
    public void testAdd() {
        Entry testEntry = new Entry(new BigDecimal(269), "Test", 1);
        testController.addEntry(new BigDecimal(269), "Test", 1);

        assertEquals(testEntry, testController.getEntries().getFirst());
    }

    @Test
    public void testSet1() {
        Entry testEntry1 = new Entry(new BigDecimal(75), "Test Entry 1", 4);
        Entry testEntry2 = new Entry(new BigDecimal(40), "Test Entry 2", 2);
        Entry testEntry3 = new Entry(new BigDecimal(23), "Test Entry 3", 1);

        testController.addEntry(testEntry1);
        testController.addEntry(testEntry2);
        testController.setEntry(1, testEntry3);

        assertEquals(testEntry3, testController.getEntries().get(1));
    }

    @Test
    public void testSet2() {
        Entry testEntry = new Entry(new BigDecimal(22), "Test",1);
        Entry repEntry = new Entry(new BigDecimal(22), "Replacement", 2);
        testController.addEntry(testEntry);
        testController.setEntry(0, repEntry.getCost(), repEntry.getName(), repEntry.getQuantity());

        assertEquals(repEntry, testController.getEntries().getFirst());
    }

    @Test
    public void testRemove() {
        Entry testEntry = new Entry(new BigDecimal(2), "Test", 1);
        testController.addEntry(testEntry);
        testController.removeEntry(0);


        assertEquals(0, testController.getEntries().size());
    }

}
