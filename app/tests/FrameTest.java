package app.tests;

import app.views.ApplicationView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class FrameTest {
    private JFrame testFrame;

    @BeforeEach
    public void Setup() {
        testFrame = new ApplicationView();
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Test
    public void run() {
        testFrame.setVisible(true);
    }
}
