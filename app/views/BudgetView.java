package app.views;

import app.Main;
import app.controllers.BudgetController;
import app.controllers.ProfileController;
import app.models.EntryModel;
import app.models.ProfileModel;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Displays the screen showing the <code>Budget</code> of a project.
 * @author Darrell Green, Jr., Zarif Mazumder
 */
public class BudgetView extends JFrame {
    private static final String TITLE_NAME = "Budget";
    private final BudgetController budgetController;
    private final ProfileController profileController;

    public BudgetView(ProfileController profileController) {
        this.profileController = profileController;
        budgetController = new BudgetController(profileController.getProjectController());
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new NavigationBar(profileController));
        this.add(displayContent(), BorderLayout.CENTER);
        this.add(new ProjectSelectBar(profileController), BorderLayout.PAGE_END);
    }

    /**
     * @author Zarif Mazumder
     * @return content
     */
    private JScrollPane displayContent() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        LinkedList<EntryModel> entries = budgetController.getEntries();
        JTable table = displayEntries(entries);
        table.setPreferredScrollableViewportSize(new Dimension(
                table.getPreferredSize().width, table.getRowHeight() * table.getRowCount()));
        JScrollPane tablePane = new JScrollPane(table);
        content.add(tablePane);
        if (profileController.getPrivilege() == ProfileModel.Privilege.ADMIN) {
            JPanel addEntryBtn = displayAddEntryButton();
            content.add(addEntryBtn);
        }
        return new JScrollPane(content);
    }

    /**
     * @author Zarif Mazumder
     * @param entries data
     * @return <code>JTable</code> of entries
     */
    private JTable displayEntries(LinkedList<EntryModel> entries) {
        if (entries.isEmpty()) return new JTable();
        String[] columnNames = {"cost", "item", "quantity"};
        String[][] rowData = new String[entries.size()][3];
        for (int i = 0; i < entries.size(); i++) {
            EntryModel entryModel = entries.get(i);
            rowData[i][0] = entryModel.getCost().toString();
            rowData[i][1] = entryModel.getName();
            rowData[i][2] = String.valueOf(entryModel.getQuantity());
        }
        return new JTable(rowData, columnNames);
    }

    /**
     * @author Zarif Mazumder
     * @return add item button
     */
    private JPanel displayAddEntryButton() {
        JPanel checkBoxItem = new JPanel();
        JButton addItemBtn = new JButton("+");
        JLabel costLabel = new JLabel("Cost");
        JTextField cost = new JTextField(4);
        JLabel itemLabel = new JLabel("Item");
        JTextField item = new JTextField(4);
        JLabel quantityLabel = new JLabel("Quantity");
        JTextField quantity = new JTextField(4);
        addItemBtn.addActionListener(e -> {
            String costText = cost.getText().trim();
            String nameText = item.getText().trim();
            String quantityText = quantity.getText().trim();
            if (isValidEntry(costText, nameText, quantityText)) {
                budgetController.addEntry(BigDecimal.valueOf(Double.parseDouble(costText)), nameText,
                        Integer.parseInt(quantityText));
            }
            Main.setCurrentView(new BudgetView(profileController));
        });
        checkBoxItem.add(addItemBtn);
        checkBoxItem.add(costLabel);
        checkBoxItem.add(cost);
        checkBoxItem.add(itemLabel);
        checkBoxItem.add(item);
        checkBoxItem.add(quantityLabel);
        checkBoxItem.add(quantity);
        return checkBoxItem;
    }

    private boolean isValidEntry(String costText, String nameText, String quantityText) {
        if (costText.isEmpty() || nameText.isEmpty() || quantityText.isEmpty()) return false;
        try {
            Double.parseDouble(costText);
            Integer.parseInt(quantityText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}