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
        this.setJMenuBar(new NavigationBar(profileController));
        this.add(displayContent(), BorderLayout.CENTER);
        this.add(new ProjectSelectBar(profileController), BorderLayout.SOUTH);
    }

    /**
     * @author Zarif Mazumder
     * @return content
     */
    private JPanel displayContent() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JTextArea doc = new JTextArea(detailController.getText());
        doc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        content.add(doc, BorderLayout.CENTER);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            detailController.setText(doc.getText());
            saveBtn.setText("Saved!");
            Timer timer = new Timer(500, f -> saveBtn.setText("Save"));
            timer.setRepeats(false);
            timer.start();
        });
        content.add(saveBtn, BorderLayout.SOUTH);
        return content;
    }
}
