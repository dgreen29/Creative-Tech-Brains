package app.views;

import app.Main;
import app.controllers.DetailController;
import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the screen showing the details of a project.
 * @author Zarif Mazumder
 */
public class DetailView extends JFrame {
    private static final String TITLE_NAME = "Detail";
    private final DetailController detailController;

    public DetailView(ProfileController profileController) {
        detailController = new DetailController(profileController.getProjectController());
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new NavigationBar());
        displayDocument();
    }

    /**
     * @author Zarif Mazumder
     */
    private void displayDocument() {
        JTextArea doc = new JTextArea(detailController.getText());
        doc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(doc, BorderLayout.CENTER);
        displaySaveButton(doc);
    }

    /**
     * @author Zarif Mazumder
     * @param document text field
     */
    private void displaySaveButton(JTextArea document) {
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            detailController.setText(document.getText());
            saveBtn.setText("Saved!");
            Timer timer = new Timer(500, f -> saveBtn.setText("Save"));
            timer.setRepeats(false);
            timer.start();
        });
        this.add(saveBtn, BorderLayout.SOUTH);
    }
}
