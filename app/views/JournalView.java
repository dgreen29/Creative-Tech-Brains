package app.views;

import app.Main;
import app.controllers.ProfileController;
import app.controllers.ProjectController;
import app.models.ItemModel;
import app.models.ProfileModel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Displays projects with progress and checklist.
 * @author Darrell Green Jr., Harman Singh, Zarif Mazumder
 */
public class JournalView extends JFrame {
    private static final String TITLE_NAME = "Journal";
    private final ProfileController profileController;
    private final ProjectController projectController;

    public JournalView(ProfileController profileController) {
        this.profileController = profileController;
        projectController = profileController.getProjectController();
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        NavigationBar navigationBar = new NavigationBar(profileController);
        this.setJMenuBar(navigationBar);
        this.add(displayContent(), BorderLayout.CENTER);
        this.add(new ProjectSelectBar(profileController), BorderLayout.SOUTH);
    }

    /**
     * @author Zarif Mazumder
     * @return content
     */
    private JScrollPane displayContent() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        LinkedList<ItemModel> checklist = projectController.getChecklist();
        content.add(displayProgressBar(checklist));
        for (int i = 0; i < checklist.size(); i++) {
            JPanel checkBoxItem = displayCheckBoxItem(i, checklist);
            content.add(checkBoxItem);
        }
        if (profileController.getPrivilege() == ProfileModel.Privilege.ADMIN) {
            JPanel addItemButton = displayAddItemButton();
            content.add(addItemButton);
        }
        return new JScrollPane(content);
    }

    /**
     * @author Zarif Mazumder
     * @param checklist list of <code>Item</code>s
     * @return progress bar
     */
    private JPanel displayProgressBar(LinkedList<ItemModel> checklist) {
        int progress = 0;
        for (ItemModel i : checklist) {
            if (i.isDone()) progress++;
        }
        JPanel progressBar = new JPanel();
        JProgressBar jProgressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, checklist.size());
        jProgressBar.setValue(progress);
        progressBar.add(jProgressBar);
        return progressBar;
    }

    /**
     * @author Zarif Mazumder
     * @param i index of <code>Item</code>
     * @param checklist list of <code>Item</code>s
     * @return <code>Item</code> as list item
     */
    private JPanel displayCheckBoxItem(int i, LinkedList<ItemModel> checklist) {
        ItemModel item = checklist.get(i);
        JPanel checkBoxPanel = new JPanel();
        JButton isDoneBtn = new JButton();
        if (item.isDone()) {
            isDoneBtn.setText("✓");
        } else {
            isDoneBtn.setText("X");
        }
        JTextField text = new JTextField(item.getText(), 25);
        text.addActionListener(e -> {
            String itemText = text.getText();
            if (!itemText.isEmpty()) {
                projectController.setItem(i, itemText, item.isDone());
                Main.setCurrentView(new JournalView(profileController));
            }
        });
        if (profileController.getPrivilege() == ProfileModel.Privilege.ADMIN) {
            isDoneBtn.addActionListener(e -> {
                String itemText = text.getText();
                if (!itemText.isEmpty()) {
                    projectController.setItem(i, itemText, !item.isDone());
                    Main.setCurrentView(new JournalView(profileController));
                }
            });
        }
        checkBoxPanel.add(isDoneBtn);
        checkBoxPanel.add(text);
        if (profileController.getPrivilege() == ProfileModel.Privilege.ADMIN) {
            JButton deleteBtn = new JButton("-");
            deleteBtn.addActionListener(e -> {
                projectController.removeItem(i);
                Main.setCurrentView(new JournalView(profileController));
            });
            checkBoxPanel.add(deleteBtn);
        }
        return checkBoxPanel;
    }

    /**
     * @author Zarif Mazumder
     * @return add item button
     */
    private JPanel displayAddItemButton() {
        JPanel addItemPanel = new JPanel();
        JButton addItemBtn = new JButton("+");
        JTextField text = new JTextField(20);
        text.addActionListener(e -> {
            String itemText = text.getText().trim();
            if (!itemText.isEmpty()) {
                projectController.addItem(itemText);
                Main.setCurrentView(new JournalView(profileController));
            }
        });
        addItemBtn.addActionListener(e -> {
            String itemText = text.getText().trim();
            if (!itemText.isEmpty()) {
                projectController.addItem(itemText);
                Main.setCurrentView(new JournalView(profileController));
            }
        });
        addItemPanel.add(addItemBtn);
        addItemPanel.add(text);
        return addItemPanel;
    }
}
