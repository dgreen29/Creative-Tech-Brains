package app.views;

import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Display's user information with <code>Profile</code> manipulation functionality.
 */
public class ProfileScreen extends JFrame {
    private static final String PROFILE_FRAME_NAME = "Profile";
    private static final String CREATE_BUTTON_NAME = "Create Profile";
    private static final String IMPORT_BUTTON_NAME = "Import Profile";
    private static final String IMPORT_SUCCESS_MESSAGE = "Profile Import success";
    private static final String IMPORT_FAIL_MESSAGE = "Profile Import fail";
    private static final String EXPORT_BUTTON_NAME = "Export Profile";
    private static final String EXPORT_SUCCESS_MESSAGE = "Profile Export success";
    private static final String EXPORT_FAIL_MESSAGE = "Profile Export fail";
    private final ProfileController profileController;
    private final int appWidth;
    private final int appHeight;
    private JPanel userInfoPanel;

    public ProfileScreen(int appWidth, int appHeight, ProfileController profileController) {
        this.appHeight = appHeight;
        this.appWidth = appWidth;
        this.profileController = profileController;
        this.setTitle(PROFILE_FRAME_NAME);
        this.setSize(this.appWidth, this.appHeight);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        userInfoPanel = displayUserInfo();

        //Place info panel on the screen
        this.add(userInfoPanel, BorderLayout.CENTER);

        //Add I/O buttons to frame
        this.add(displayButtonPanel(), BorderLayout.PAGE_END);

    }

    private JPanel displayUserInfo() {
        JPanel userInfoPanel = new JPanel(new GridLayout(1, 2));
        userInfoPanel.add(new JLabel("User: " + this.profileController.getName()));
        userInfoPanel.add(new JLabel("Email: " + this.profileController.getEmail()));
        return userInfoPanel;
    }

    private JPanel displayButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(displayCreateButton());
        panel.add(displayImportButton());
        panel.add(displayExportButton());
        return panel;
    }

    private JButton displayCreateButton() {
        JButton createBtn = new JButton(CREATE_BUTTON_NAME);
        createBtn.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setSize((int) (appWidth/1.5), (int) (appHeight/1.5));
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

    private void updateUserInfoDisplay() {
        this.remove(userInfoPanel);
        userInfoPanel = displayUserInfo();
        this.add(userInfoPanel, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

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
            dialog.setSize(appWidth/2, appHeight/2);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        return importBtn;
    }

    private JButton displayExportButton() {
        JButton exportBtn = new JButton(EXPORT_BUTTON_NAME);
        exportBtn.addActionListener(e -> {
            String exportStatus = profileController.exportProfile() ? EXPORT_SUCCESS_MESSAGE : EXPORT_FAIL_MESSAGE;
            JDialog dialog = new JDialog();
            dialog.add(new JLabel(exportStatus));
            dialog.setSize(appWidth/2, appHeight/2);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        return exportBtn;
    }
}
