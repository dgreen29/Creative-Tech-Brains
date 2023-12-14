package app.views;

import app.Main;
import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.controllers.ProjectController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The BudgetView class represents the graphical user interface
 * for managing the project's budget.
 * It extends the JFrame class to create a window.
 *
 * @author Darrell Green, Jr. (DJ Green)
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
    private DefaultListModel<JLabel> listModel;
    private JList<JLabel> itemList;
    private JLabel totalCostLabel;
    private double totalCost = 0.0;

    /**
     * Creates an instance of BudgetView.
     *
     * @param profileController the ProfileController object used by the BudgetView
     */
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

        totalCostLabel = new JLabel("Total Cost: $0");
        newItemPanel.add(totalCostLabel);

        uploadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG, GIF, PNG Images", "jpg","jpeg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
            }
        });
        newItemPanel.add(uploadButton);

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JLabel selectedItem = itemList.getSelectedValue();
                    if (selectedItem != null) {
                        String itemDescription = selectedItem.getToolTipText();
                        JOptionPane.showMessageDialog(null, itemDescription, "Item Description", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
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

                    String tooltip = "Name: " + itemName +
                            "\nCost: $" + String.format("%.2f", itemCost) +
                            "\nQuantity: " + itemQuantity +
                            "\nTotal Cost: $" + String.format("%.2f", itemCost * itemQuantity);

                    JLabel itemLabel = new JLabel(itemName);
                    itemLabel.setToolTipText(tooltip);

                    listModel.addElement(itemLabel);

                    totalCost += itemCost * itemQuantity;
                    totalCostLabel.setText("Total Cost: $" + String.format("%.2f", totalCost));

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
        newItemPanel.add(addItemButton);
    }

    /**
     * Updates the total cost label in the BudgetView with the
     * current total cost value.
     * The method formats the total cost value using a
     * DecimalFormat object and updates the label's text.
     *
     * Note: This method does not return any value
     *
     * @author Darrell Green, Jr. (DJ Green)
     */
    void updateTotalCost(){
      DecimalFormat df = new DecimalFormat("#,###.00");
      String totalCostStr = df.format(totalCost);
        totalCostLabel.setText("Total Cost: $" + totalCostStr);
    }
}