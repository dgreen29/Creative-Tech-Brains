package app.views;

import app.Main;
import app.controllers.DetailController;
import app.controllers.ProfileController;
import app.controllers.*;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DetailView extends JFrame {
    private static final String TITLE_NAME = "Detail";
    private final DetailController detailController;
    private JTextField logTitleField;
    private JTextArea doc;
    private JList<String> logsList;
    private DefaultListModel<String> listModel;

    public DetailView(ProfileController profileController) {
        detailController = new DetailController(profileController.getProjectController());
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new NavigationBar());

        listModel = new DefaultListModel<>();
        // Start of debug print statements
        System.out.println("Log Titles: " + Arrays.toString(Arrays.stream(detailController.getLogTitles()).toArray()));
        // End of debug print statements
        for (String title : detailController.getLogTitles()){
            listModel.addElement(title);
        }
        System.out.println("Test List Model: " + Arrays.toString(listModel.toArray()));

        logsList = new JList<>(listModel);
        logsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        logsList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                String selectedLogTitle = logsList.getSelectedValue();
                if (selectedLogTitle != null) {
                    detailController.selectLog(selectedLogTitle); // assuming selectLog sets current log in DetailController
                    refreshDetailsPanel();
                }
            }
        });

        this.add(new JScrollPane(logsList), BorderLayout.WEST);
        this.add(displayContent(), BorderLayout.CENTER);
        this.add(new ProjectSelectBar(), BorderLayout.SOUTH);
    }

    private void refreshDetailsPanel() {
        logTitleField.setText(detailController.getCurrentLogTitle()); // assuming getCurrentLogTitle() returns the title of the log currently being edited
        doc.setText(detailController.getText());
    }

    private JPanel displayContent() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(10, 10));

        logTitleField = new JTextField(detailController.getCurrentLogTitle());
        content.add(logTitleField, BorderLayout.NORTH);

        doc = new JTextArea(detailController.getText());
        doc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        doc.setLineWrap(true);
        doc.setFont(new Font("Serif", Font.PLAIN, 18));
        content.add(new JScrollPane(doc), BorderLayout.CENTER);

            JButton saveBtn = new JButton("Save");
            saveBtn.addActionListener(e -> {
                int selectedIndex = logsList.getSelectedIndex();
                if (selectedIndex != -1) { // Check that we do return an index before we remove an object
                    listModel.remove(selectedIndex);
                    listModel.add(selectedIndex, logTitleField.getText());
                    logsList.setSelectedIndex(selectedIndex);
                }

            saveBtn.setText("Saving...");
            Timer timer = new Timer(1000, f -> {
                saveBtn.setText("Saved!");
                Timer revertTimer = new Timer(1000, g -> {
                    saveBtn.setText("Save");
                });
                revertTimer.setRepeats(false);
                revertTimer.start();
            });
            timer.setRepeats(false);
            timer.start();

                detailController.setText(doc.getText());
                String oldTitle = detailController.getCurrentLogTitle();
                detailController.setLogTitle(logTitleField.getText());
        });
        content.add(saveBtn, BorderLayout.SOUTH);
        return content;
    }
}