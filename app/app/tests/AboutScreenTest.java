package app.tests;

import app.controllers.AboutController;
import app.controllers.ProfileController;
import app.views.AboutScreen;
import app.views.ApplicationView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class AboutScreenTest {
    JFrame testFrame;
    AboutScreen testScreen;

    @BeforeEach
    public void Setup() {
        testFrame = new ApplicationView();
        testScreen = new AboutScreen((ApplicationView) testFrame, new AboutController(), new ProfileController());
        testScreen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @Test
    public void AboutTest() {
        testScreen.setVisible(true);
    }
}
