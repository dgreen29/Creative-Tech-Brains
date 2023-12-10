package app.views;

import app.Main;
import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public final class NavigationBar extends JMenuBar {

    private ProfileController pc;
    private String currentProfileName;

    public NavigationBar() {

        pc = Main.getProfileController();
        currentProfileName = pc.getName();

        // Create a String array for combo box items
        String[] menuItems = {"MENU", "Budget", "Detail", "Projects", currentProfileName, "About"};

        // Create a JComboBox instance with the items
        JComboBox<String> menuComboBox = new JComboBox<>(menuItems);
        menuComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuComboBox.setAlignmentY(Component.TOP_ALIGNMENT);
        menuComboBox.setSize(5, 50);
        menuComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selected = (String) e.getItem();
                // Prevent action on default item
                if (!selected.equals("MENU")) {
                    handleMenuSelection(selected);
                }
            }
        });

        // Ensure the combo box fills the entire width of the NavigationBar
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(menuComboBox);
        this.add(Box.createHorizontalGlue());
    }

    public void handleMenuSelection(String selected) {
        if ("Budget".equals(selected)) {
            Main.setCurrentView(new BudgetView(pc));
        } else if ("Detail".equals(selected)) {
            Main.setCurrentView(new DetailView(pc));
        } else if ("Projects".equals(selected)) {
            Main.setCurrentView(new ApplicationView(pc));
        } else if (currentProfileName.equals(selected)) {
            Main.setCurrentView(new ProfileScreen(pc));
        } else if ("About".equals(selected)) {
            AboutScreen dialog = new AboutScreen(pc);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
    }
}