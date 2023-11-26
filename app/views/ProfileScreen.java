package app.views;

import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends JFrame {
    private final String PROFILE_FRAME_NAME = "Profile";
    private final String IMPORT = "Import Profile";
    private final String EXPORT = "Export Profile";
    private ProfileController profileController;
    private int appWidth;
    private int appHeight;

    public ProfileScreen(int appWidth, int appHeight, ProfileController profileController) {
        this.appHeight = appHeight;
        this.appWidth = appWidth;
        this.profileController = profileController;
        this.setTitle(PROFILE_FRAME_NAME);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
            //Placeholder code until relevant code to import profile is added to model.
            //TODO: Replace with importProfile() once implemented by other team.
            //Make File Chooser
            JFileChooser importFileChooser = new JFileChooser();
            //Set the file chooser to only allow .ser-type files

            //Open dialog and check whether a file location was successfully selected
            if(importFileChooser.showDialog(null, null) == JFileChooser.APPROVE_OPTION) {
                //call import file method. Dialog popup is place holder
                JDialog dialog = new JDialog();
                dialog.add(new JLabel(importFileChooser.getSelectedFile().getAbsolutePath()));
                dialog.setSize(appWidth/2, appHeight/2);
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }

        });

        exportBtn = new JButton(EXPORT);
        exportBtn.addActionListener(e -> {
            //Placeholder code until relevant code to export profile is added to model.
            //TODO: Replace with exportProfile() once implemented by other team.
            JDialog dialog = new JDialog();
            dialog.add(new JLabel("Profile Exported!"));
            dialog.setSize(appWidth/2, appHeight/2);
            dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });

        //Add buttons to frame
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(importBtn);
        buttonsPanel.add(exportBtn);
        this.add(buttonsPanel, BorderLayout.PAGE_END);

    }
}
