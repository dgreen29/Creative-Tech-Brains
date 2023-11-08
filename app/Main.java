package app;

import app.views.ApplicationView;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new ApplicationView();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true); //
    }
}
