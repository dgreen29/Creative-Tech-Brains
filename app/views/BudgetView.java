package app.views;

import app.Main;
import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Displays the screen showing the <code>Budget</code> of a project.
 * @author Darrell Green, Jr., Zarif Mazumder
 */
public class BudgetView extends JFrame {
    private static final String TITLE_NAME = "Budget";
    private final BudgetController budgetController;
    private final ProfileController profileController;
    private final ProjectController projectController;

    private JTextField nameField;
    private JTextField costField;
    private JTextField quantityField;
    private JButton uploadButton;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;

    public BudgetView(ProfileController profileController) {
        this.profileController = profileController;
        projectController = profileController.getProjectController();
        budgetController = new BudgetController(projectController);
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new NavigationBar());

        JPanel newItemPanel = new JPanel(new GridLayout(0, 2));

        newItemPanel.add(new JLabel("Name: "));
        nameField = new JTextField();
        newItemPanel.add(nameField);

        newItemPanel.add(new JLabel("Cost: "));
        costField = new JTextField();
        newItemPanel.add(costField);

        newItemPanel.add(new JLabel("Quantity: "));
        quantityField = new JTextField();
        newItemPanel.add(quantityField);

        newItemPanel.add(new JLabel("Upload image: "));
        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF Images", "jpg", "gif");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                // handle the selected image file
            }
        });
        newItemPanel.add(uploadButton);

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        this.add(newItemPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(e -> {
            String newItem = "Name: " + nameField.getText() + ", Cost: " + costField.getText() + ", QTY: " + quantityField.getText();
            listModel.addElement(newItem);
        });
        this.add(addItemButton, BorderLayout.SOUTH);
        this.add(new ProjectSelectBar(profileController), BorderLayout.PAGE_END);
    }
}