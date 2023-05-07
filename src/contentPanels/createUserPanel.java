package contentPanels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import UI_Formatter.fileUIController;
import UI_Formatter.colorPalette;
import Controller.controller;

import Objects.LoginObject;


public class createUserPanel extends JPanel{

    private static fileUIController fileUIController = new fileUIController();
    JTextField usernameField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;
    JLabel wrongPasswordLabel;


    public createUserPanel(){


        //middle panel containing most of the content 
        JPanel middlePanel = new JPanel();
        middlePanel.setPreferredSize(new Dimension(850, 1000));
        middlePanel.setBackground(colorPalette.background);
        bottomMiddle(middlePanel);

        //setting the panels layout to border layout
        setLayout(new BorderLayout());

        //creatting a wrapper to layer the middle panels 
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        wrapperPanel.add(middlePanel);

        //adding panels to format the main panel
        add(wrapperPanel, BorderLayout.CENTER);
    }


    /**
     * Designing the main content of the panel
     * @param middlePanel where content should be added
     */
    private void bottomMiddle(JPanel middlePanel){

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

        JLabel headerLabel;
        headerLabel = new JLabel("<html>Please create a unique<br>Username Password<br><br></html>");
        middlePanel.add(headerLabel);

        // Create the username label and input field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(300, 30)); // Set the preferred size to be 300x30 pixels
        middlePanel.add(usernameLabel);
        middlePanel.add(usernameField);

        // Create the password label and input field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 30)); // Set the preferred size to be 300x30 pixels
        middlePanel.add(passwordLabel);
        middlePanel.add(passwordField);

        // Create the password label and input field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setMaximumSize(new Dimension(300, 30)); // Set the preferred size to be 300x30 pixels
        middlePanel.add(confirmPasswordLabel);
        middlePanel.add(confirmPasswordField);

        // Label to show when password is wrong
        wrongPasswordLabel = new JLabel("");
        wrongPasswordLabel.setVisible(false);
        wrongPasswordLabel.setForeground(new Color(255, 0, 0));
        middlePanel.add(wrongPasswordLabel);

        // Create the submit button and attach an ActionListener to call the checkPassword function
        JButton submitButton = new JButton("Signup");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signup();
            }
        });
        middlePanel.add(submitButton);
    }

    /**
     * Calling instance of fileUIController to change the card
     */
    //public static LoginObject createUser(String username, String password){
    public static LoginObject createUser(String username, String password){
        return controller.getInstance().createUser(username, password);
        //return controller.getInstance().createUser(username, password);
    }

    private void signup() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmedPassword = new String(confirmPasswordField.getPassword());
        if(!confirmedPassword.equals(password)){
            // show fail message for passwords not matching
            wrongPasswordLabel.setVisible(true);
            wrongPasswordLabel.setText("Passwords do not match");
        }
        LoginObject createLogin = createUser(username, password);
        if(createLogin.isSuccess()){
            usernameField.setText("");
            passwordField.setText("");
            wrongPasswordLabel.setVisible(false);
            controller.getInstance().loginUser(username, password);
            fileUIController.changeCard("No Files");
            controller.loginToHome();
        } else {
            // show fail message
            wrongPasswordLabel.setVisible(true);
            wrongPasswordLabel.setText(createLogin.getMessage());
        }
    }

}