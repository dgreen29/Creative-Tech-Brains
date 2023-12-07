package app.views;

import app.Main;
import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Displays user information with <code>Profile</code> manipulation functionality.
 * @author Darrell Green, Jr., Harman Singh, Zarif Mazumder
 */
public class ProfileScreen extends JFrame {
    private static final String TITLE_NAME = "Profile";
    private static final String CREATE_BUTTON_NAME = "Create Profile";
    private static final String IMPORT_BUTTON_NAME = "Import Profile";
    private static final String IMPORT_SUCCESS_MESSAGE = "Profile Import success";
    private static final String IMPORT_FAIL_MESSAGE = "Profile Import fail";
    private static final String EXPORT_BUTTON_NAME = "Export Profile";
    private static final String EXPORT_SUCCESS_MESSAGE = "Profile Export success";
    private static final String EXPORT_FAIL_MESSAGE = "Profile Export fail";
    private final ProfileController profileController;
    private JPanel userInfoPanel;

    public ProfileScreen(ProfileController profileController) {
        this.profileController = profileController;
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        userInfoPanel = displayUserInfo();
        this.setJMenuBar(new NavigationBar());
        this.add(userInfoPanel, BorderLayout.CENTER); // Place info panel on the screen
        this.add(displayButtonPanel(), BorderLayout.PAGE_END); // Add I/O buttons to frame
    }

    /**
     * Creates a panel to display user profile information
     * @author Harman Singh
     * @return user info <code>JPanel</code>
     */
    private JPanel displayUserInfo() {
        JPanel userInfoPanel = new JPanel(new GridLayout(1, 2));
        userInfoPanel.add(new JLabel("User: " + this.profileController.getName()));
        userInfoPanel.add(new JLabel("Email: " + this.profileController.getEmail()));
        return userInfoPanel;
    }

    /**
     * Creates a panel that contains the buttons on the profile page
     * @author Zarif Mazumder
     * @return button <code>JPanel</code>
     */
    private JPanel displayButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(displayCreateButton());
        panel.add(displayImportButton());
        panel.add(displayExportButton());
        return panel;
    }

    /**
     * Creates a button used to create a new user profile
     * @author Zarif Mazumder
     * @return create <code>JButton</code>
     */
    private JButton displayCreateButton() {
        JButton createBtn = new JButton(CREATE_BUTTON_NAME);
        createBtn.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setSize((int) (Main.APP_WIDTH/1.5), (int) (Main.APP_HEIGHT/1.5));
            dialog.setLayout(new FlowLayout());
            JTextField nameField = new JTextField("", 10);
            JTextField emailField = new JTextField("", 10);
            dialog.add(nameField);
            dialog.add(emailField);
            JButton submitBtn = new JButton("Submit");
            submitBtn.addActionListener(f -> {
                profileController.createProfile(nameField.getText(), emailField.getText());
                updateUserInfoDisplay();
                dialog.dispose();
            });
            dialog.add(submitBtn);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        return createBtn;
    }

    /**
     * Updates the user info <code>JPanel</code>.
     * @author Zarif Mazumder
     */
    private void updateUserInfoDisplay() {
        this.remove(userInfoPanel);
        userInfoPanel = displayUserInfo();
        this.add(userInfoPanel, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    /**
     * Creates a button that is used to import a profile
     * @author Harman Singh, Zarif Mazumder
     * @return import <code>JButton</code>
     */
    private JButton displayImportButton() {
        JButton importBtn = new JButton(IMPORT_BUTTON_NAME);
        importBtn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));
            jfc.showOpenDialog(null);
            String importStatus = profileController.importProfile(jfc.getSelectedFile()) ?
                    IMPORT_SUCCESS_MESSAGE : IMPORT_FAIL_MESSAGE;
            updateUserInfoDisplay();
            JDialog dialog = new JDialog();
            dialog.add(new JLabel(importStatus));
            dialog.setSize(Main.APP_WIDTH/2, Main.APP_WIDTH/2);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        return importBtn;
    }

    /**
     * Creates a button that can export a user profile
     * @author Harman Singh
     * @return export <code>JButton</code>
     */
    private JButton displayExportButton() {
        JButton exportBtn = new JButton(EXPORT_BUTTON_NAME);
        exportBtn.addActionListener(e -> {
            String exportStatus = profileController.exportProfile() ? EXPORT_SUCCESS_MESSAGE : EXPORT_FAIL_MESSAGE;
            JDialog dialog = new JDialog();
            dialog.add(new JLabel(exportStatus));
            dialog.setSize(Main.APP_WIDTH/2, Main.APP_HEIGHT/2);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        return exportBtn;
    }
}
