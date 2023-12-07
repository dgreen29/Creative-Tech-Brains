package app;

import app.views.ApplicationView;
import javax.swing.*;
import java.awt.event.ActionEvent;

/*
 * @author Darrell Green, Jr. (DJ Green)
 * @author Zarif Mazumder
 * @author Harman Singh
 * @author Vindhriko Chandran Cain
 *
 * @version 11.8.23
 *
 * Program Purpose: The purpose of this program is to satisfy the
 * Project UI and About Screen requirements laid out in the project
 * description as referenced by the Client Interview.
 */

/**
 * This is the Main driver class of the entire program.
 */
public class Main implements ActionListener, java.awt.event.ActionListener {
    private static JTextField userText;
    private static JTextField emailText;
    private static JLabel welcomeLabel;
    private static JLabel userLabel;
    private static JLabel emailLabel;
    private static JButton createButton;

    /**
     * The main method is the driver method for the entire program.
     * @param args A generic String array that gets passed into the
     *             method by default.
     */
    public static void main(String[] args) {

        /*
        Creates a new J Frame and initializes it.
         */
        JFrame frame = new ApplicationView();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        /*
        Sets the default close operation on the frame.
         */
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*
        The visibility here is set to true in order for the frame to be
        displayed on the screen.
         */
        frame.setVisible(true);

        userLabel = new JLabel("Name:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        emailLabel = new JLabel("Email:"  );
        emailLabel.setBounds(10,50,80,25);
        panel.add(emailLabel);

        emailText = new JTextField(20);
        emailText.setBounds(100,50,165,25);
        panel.add(emailText);

        createButton = new JButton("Create");
        createButton.setBounds(10,80,80,25);
        createButton.addActionListener(new Main());
        panel.add(createButton);

        welcomeLabel = new JLabel("");
        welcomeLabel.setBounds(10,110,500,25);
        panel.add(welcomeLabel);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String email = emailText.getText();
        if(user.equals("") || email.equals(""))
            welcomeLabel.setText("Please enter a valid username and email.");
        else
            welcomeLabel.setText("Account created successfully.");


    }
}
