package app.views;

import app.Main;
import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

class TooltipJListCellRenderer extends DefaultListCellRenderer {
    private final ArrayList<String> tooltips;

    public TooltipJListCellRenderer(ArrayList<String> tooltips){
        this.tooltips = tooltips;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        String tooltip = tooltips.size() > index ? tooltips.get(index) : null;
        component.setToolTipText(tooltip);
        return component;
    }
}

public class BudgetView extends JFrame {
    private static final String TITLE_NAME = "Budget";
    private final BudgetController budgetController;
    private final ProfileController profileController;
    private final ProjectController projectController;

    private JTextField nameField;
    private JTextField costField;
    private JTextField quantityField;
    private JButton uploadButton;
    private DefaultListModel<JLabel> listModel;
    private JList<JLabel> itemList;

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
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                // handle the selected image file...
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
            String itemName = nameField.getText();

            try {
                double itemCost = Double.parseDouble(costField.getText());
                int itemQuantity = Integer.parseInt(quantityField.getText());

                if (itemName != null && !itemName.trim().isEmpty()) {
                    budgetController.addItem(itemName, itemCost, itemQuantity);

                    String tooltip = "Name: " + itemName + ", Cost: " + String.format("%.2f", itemCost) + ", QTY: " + itemQuantity;

                    JLabel itemLabel = new JLabel(itemName);
                    itemLabel.setToolTipText(tooltip);

                    listModel.addElement(itemLabel);

                    SwingUtilities.invokeLater(() -> {
                        itemList.revalidate();
                        itemList.repaint();
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Item name cannot be empty. Please try again.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cost and Quantity should be valid numbers. Please enter again.");
            }
        });
        this.add(addItemButton, BorderLayout.SOUTH);
        this.add(new ProjectSelectBar(), BorderLayout.PAGE_END);
        newItemPanel.add(addItemButton);
    }
}