package app.views;

import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProfileScreen extends JFrame {
    private static final String PROFILE_FRAME_NAME = "Profile";
    private static final String IMPORT = "Import Profile";
    private static final String EXPORT = "Export Profile";
    private ProfileController profileController;
    private int appWidth;
    private int appHeight;

    public ProfileScreen(int appWidth, int appHeight, ProfileController profileController) {
        this.appHeight = appHeight;
        this.appWidth = appWidth;
        this.profileController = profileController;
        this.setTitle(PROFILE_FRAME_NAME);
        this.setSize(this.appWidth, this.appHeight);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());


        //Initializing JPanel that contains the User's Info.
        JPanel userInfoPanel = new JPanel(new GridLayout(1, 2));
        //Add user's info (username and email) to the panel
        userInfoPanel.add(new JLabel("User: " + this.profileController.getName()));
        userInfoPanel.add(new JLabel("Email: " + this.profileController.getEmail()));
        //Place info panel on the screen
        this.add(userInfoPanel, BorderLayout.CENTER);


        //Create buttons to import and export
        JButton importBtn, exportBtn;
        importBtn = new JButton(IMPORT);

        importBtn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(null);
            String importStatus = profileController.importProfile(jfc.getSelectedFile()) ?
                    "Profile Import success" : "Profile Import fail";
            JDialog dialog = new JDialog();
            dialog.add(new JLabel(importStatus));
            dialog.setSize(appWidth/2, appHeight/2);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        exportBtn = new JButton(EXPORT);
        exportBtn.addActionListener(e -> {
            String exportStatus = profileController.exportProfile() ? "Profile Export success" : "Profile Export fail";
            JDialog dialog = new JDialog();
            dialog.add(new JLabel(exportStatus));
            dialog.setSize(appWidth/2, appHeight/2);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });

        //Add buttons to frame
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(importBtn);
        buttonsPanel.add(exportBtn);
        this.add(buttonsPanel, BorderLayout.PAGE_END);

    }
}
