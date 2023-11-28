package app.tests;

import app.controllers.AboutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Author: Harman Singh
 */

class AboutControllerTest {
    private AboutController testController;

    @BeforeEach
    public void setup() {
        testController = new AboutController();
    }

    @Test
    public void teamTest() {
        assertArrayEquals(new String []{"Darrell Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
                "Zarif Mazumder"}, testController.getTeam());
    }
}