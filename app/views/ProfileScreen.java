package app.views;

import app.Main;
import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

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
    private static final String INVALID_NAME_ERROR_MESSAGE = "Name is invalid.";
    private static final String INVALID_EMAIL_ERROR_MESSAGE = "Email is invalid.";
    private final ProfileController profileController;
    private JPanel content;

    public ProfileScreen(ProfileController profileController) {
        this.profileController = profileController;
        this.setTitle(TITLE_NAME);
        this.setSize(Main.APP_WIDTH, Main.APP_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new NavigationBar());
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
        content.add(displayUserInfo(), BorderLayout.CENTER); // Place info panel on the screen
        content.add(displayButtonPanel(), BorderLayout.PAGE_END); // Add I/O buttons to frame
        this.content = content;
        return content;
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
     * @author Darrell Green, Jr. (DJ Green), Zarif Mazumder
     * @return create <code>JButton</code>
     */
    private JButton displayCreateButton() {
        JButton createBtn = new JButton(CREATE_BUTTON_NAME);
        JLabel errorMsg = new JLabel("");
        errorMsg.setForeground(Color.RED);
        errorMsg.setVisible(false);
        createBtn.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setSize((Main.APP_WIDTH/2), (int) (Main.APP_HEIGHT/1.5));
            dialog.setLayout(new FlowLayout());
            JTextField nameField = new JTextField("", 15);
            JTextField emailField = new JTextField("", 15);
            dialog.add(new JLabel("Name:"));
            dialog.add(nameField);
            dialog.add(new JLabel("Email:"));
            dialog.add(emailField);
            JButton submitBtn = generateSubmitBtn(nameField, emailField, dialog, errorMsg);
            dialog.add(submitBtn);
            dialog.add(errorMsg);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        return createBtn;
    }

    private JButton generateSubmitBtn(JTextField nameField, JTextField emailField, JDialog dialog, JLabel errorMsg) {
        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(f -> {
            ArrayList<ProfileController.invalidCredentials> validationMessages = profileController.validateProfile(
                    nameField.getText(), emailField.getText());
            if (validationMessages.get(0) == ProfileController.invalidCredentials.IS_VALID) {
                profileController.createProfile(nameField.getText(), emailField.getText());
                dialog.dispose();
                updateContentDisplay();
            } else {
                displayValidationError(validationMessages, errorMsg, dialog);
            }
        });
        return submitBtn;
    }

    private void displayValidationError(ArrayList<ProfileController.invalidCredentials> validationMessages,
                                        JLabel errorMsg, JDialog dialog) {
        StringBuilder sb = new StringBuilder();
        for (ProfileController.invalidCredentials iC : validationMessages) {
            if (iC == ProfileController.invalidCredentials.NAME) {
                sb.append(INVALID_NAME_ERROR_MESSAGE).append(" ");
            }
            if (iC == ProfileController.invalidCredentials.EMAIL) {
                sb.append(INVALID_EMAIL_ERROR_MESSAGE).append(" ");
            }
        }
        errorMsg.setText(sb.toString());
        errorMsg.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
    }

    /**
     * Updates the user info <code>JPanel</code>.
     * @author Zarif Mazumder
     */
    private void updateContentDisplay() {
        this.remove(content);
        content = displayContent();
        this.add(content, BorderLayout.CENTER);
        this.setJMenuBar(new NavigationBar());
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
            updateContentDisplay();
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
