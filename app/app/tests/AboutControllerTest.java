package app.tests;

import app.controllers.AboutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AboutControllerTest {
    private AboutController testController;

    @BeforeEach
    public void setup() {
        testController = new AboutController();
    }

    @Test
    public void VersionTest() {
        assertEquals( "1.0.0",testController.getVersion());
    }

    @Test
    public void TeamTest() {
        assertArrayEquals(new String []{"Darrel Green, Jr.", "Harman Singh", "Vindhriko Chandran Cain",
                "Zarif Mazumder"}, testController.getTeam());
    }
}