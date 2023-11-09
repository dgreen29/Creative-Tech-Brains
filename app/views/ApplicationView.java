package app.views;

import app.controllers.AboutController;
import app.controllers.ProfileController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 * 
 * @version 11.8.23
 * 
 * Application view class
 */
public class ApplicationView extends JFrame {
	/**
	 * String containing the app's name.
	 */
    private final String APPLICATION_NAME = "the App";
    /**
     * String containing the about buttons name.
     */
    private final String ABOUT_BUTTON_NAME = "About";
    /**
     * Int containing width of app.
     */
    private final int appWidth = 500;
    /**
     * Int containing height of app.
     */
    private final int appHeight = 500;
    /**
     * Creates AboutController object.
     */
    private final AboutController aboutController;
    /**
     * Creates ProfileController object.
     */
    private final ProfileController profileController;
    /**
     * ApplicationView constructor.
     */
    public ApplicationView() {
        aboutController = new AboutController();
        profileController = new ProfileController();
        this.setTitle(APPLICATION_NAME);
        this.setSize(appWidth, appHeight);
        this.setLocationRelativeTo(null);
        this.add(displayAboutBtn());
    }
    /**
     * Creates about button.
     * @return
     */
    private JButton displayAboutBtn() {
        JButton aboutBtn = new JButton(ABOUT_BUTTON_NAME);
        aboutBtn.addActionListener(new AboutListener(this, aboutController, profileController));
        return aboutBtn;
    }
    /**
     * returns app's width.
     * @return
     */
    public int getAppWidth() {
        return appWidth;
    }
    /**
     * returns app's height.
     * @return
     */
    public int getAppHeight() {
        return appHeight;
    }
    /*
     * @author Darrell Green, Jr. (DJ Green)
     * @author Zarif Mazumder
     * @author Harman Singh
     * @author Vindhriko Chandran Cain
     * 
     * @version 11.8.23
     * 
     * AboutListener class
     */
    private static class AboutListener implements ActionListener {
    	/**
    	 * Creates ApplicationView object.
    	 */
        private final ApplicationView applicationView;
        /**
         * creates AboutController object.
         */
        private final AboutController aboutController;
        /**
         * Creates ProfileController object.
         */
        private final ProfileController profileController;
        /**
         * AboutListener constructor.
         * @param applicationView
         * @param aboutController
         * @param profileController
         */
        public AboutListener(
                ApplicationView applicationView,
                AboutController aboutController,
                ProfileController profileController) {
            this.applicationView = applicationView;
            this.aboutController = aboutController;
            this.profileController = profileController;
        }
        /**
         * opens the about screen.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            try {
                AboutScreen dialog = new AboutScreen(applicationView, aboutController, profileController);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
