package app.views;

import app.controllers.ProfileController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class ProfileScreen extends JFrame {
    private final String PROFILE_FRAME_NAME = "Profile";
    private final String IMPORT = "Import Profile";
    private final String EXPORT = "Export Profile";
    private final String FILE_FILTER_DISPLAY_TEXT = "Serialized Files (*.ser)";
    private final String SERIALIZED_FILE_EXTENSION = "ser";
    private ProfileController profileController;
    private JMenuBar menuBar;
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

        prepareGUI();

    }
    private void prepareGUI() {

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
            //Create a filter
            FileNameExtensionFilter serFileTypeFilter = new FileNameExtensionFilter(FILE_FILTER_DISPLAY_TEXT, SERIALIZED_FILE_EXTENSION);
            //Apply the filter to file chooser
            importFileChooser.setFileFilter(serFileTypeFilter);
            //Open dialog and check whether a file location was successfully selected
            if(importFileChooser.showDialog(null, null) == JFileChooser.APPROVE_OPTION) {
                //call import file method. Dialog popup is placeholder
                JDialog dialog = createGenericDialogByText(importFileChooser.getSelectedFile().getAbsolutePath());

                dialog.setVisible(true);
            }

        });

        exportBtn = new JButton(EXPORT);
        exportBtn.addActionListener(e -> {
            //Placeholder code until relevant code to export profile is added to model.
            //TODO: Replace with exportProfile() once implemented by other team.
            JDialog dialog = createGenericDialogByText("Profile Exported!");

            dialog.setVisible(true);
        });

        //Add buttons to frame
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(importBtn);
        buttonsPanel.add(exportBtn);
        this.add(buttonsPanel, BorderLayout.PAGE_END);

        //Setup menu
        prepareMenu();

    }

    private void prepareMenu() {
        //initialize menuBar
        this.menuBar = new JMenuBar();

        //setup file menu
        JMenu fileMenu = new JMenu("File");

        //Home option leads back to ApplicationView
        JMenuItem homeOption = new JMenuItem("Home");
        homeOption.addActionListener(e -> {
            try {
                ApplicationView applicationView = new ApplicationView();
                applicationView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                applicationView.setVisible(true);
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        //Profile option leads back to ProfileScreen
        JMenuItem profileOption = new JMenuItem("Profile");
        profileOption.addActionListener(e -> {
            try {
                ProfileScreen profileScreen = new ProfileScreen(appWidth, appHeight, profileController);
                profileScreen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                profileScreen.setVisible(true);
                this.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        //Exits application
        JMenuItem exitOption = new JMenuItem("Exit");
        exitOption.addActionListener(e->{
            try {
                for(Frame f : Frame.getFrames()) {
                    if(!f.equals(this)) {
                        f.dispose();
                    }
                }
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        //add to file menu
        fileMenu.add(homeOption);
        fileMenu.add(profileOption);
        fileMenu.add(exitOption);

        //add file menu to menu bar
        this.menuBar.add(fileMenu);

        //Attach to frame
        this.setJMenuBar(this.menuBar);
    }

    private JDialog createGenericDialogByText(final String stringToDisplay) {
        JDialog dialog = new JDialog();
        dialog.add(new JLabel(stringToDisplay));
        dialog.setSize(appWidth/2, appHeight/2);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        return dialog;
    }
}
