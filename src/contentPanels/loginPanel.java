package contentPanels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import UI_Formatter.fileUIController;
import UI_Formatter.colorPalette;
import Controller.controller;
import Managers.LoginManager;
import Objects.loginObject;

public class loginPanel extends JPanel{

    private static fileUIController fileUIController = new fileUIController();
    JTextField usernameField;
    JPasswordField passwordField;
    JLabel wrongPasswordLabel;
    static LoginManager LoginManager = new LoginManager();

    public loginPanel(){


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

        // Label to show when password is wrong
        wrongPasswordLabel = new JLabel(" ");

        wrongPasswordLabel.setVisible(false);
        wrongPasswordLabel.setForeground(new Color(255, 0, 0));
        middlePanel.add(wrongPasswordLabel);

        // Create the submit button and attach an ActionListener to call the checkPassword function
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkPassword();
            }
        });
        middlePanel.add(submitButton);

        // Create a new User
        JLabel signUpLabel = new JLabel();
        signUpLabel.setText("<html><b><font color='blue'><br> Sign Up </font></b></html>");
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetLoginUI();
                controller.getInstance().changeCard("Create User");
            }
        });
        middlePanel.add(signUpLabel);
    }

    private void resetLoginUI(){
        wrongPasswordLabel.setVisible(false);
        wrongPasswordLabel.setText("");
        usernameField.setText("");
        passwordField.setText("");
    }

    /**
     * Calling instance of fileUIController to change the card
     */
    public static loginObject login(String username, String password){
        return Managers.LoginManager.loginUser(username, password);
    }

    private void checkPassword() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        loginObject login = login(username, password);
        if(login.isSuccess()){
            resetLoginUI();
            fileUIController.changeCard("No Files");
            Managers.LoginManager.loginToHome();
        } else {
            // show fail message
            wrongPasswordLabel.setVisible(true);
            wrongPasswordLabel.setText(login.getMessage());
        }
    }
}
