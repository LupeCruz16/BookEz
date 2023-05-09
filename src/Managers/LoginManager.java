package Managers;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import Controller.controller;
import Objects.loginObject;
import contentPanels.homePanel;

public class LoginManager {
    
    private static String username;
    private static Map<String, String> credentials;

    public LoginManager(){
        credentials = new HashMap<>();
    }

    public static void loginToHome() {
        controller.getInstance().changeCard("Homescreen");
    }

    // Login the user
    public static loginObject loginUser(String username, String password){
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        // check user
        if(!credentials.containsKey(username)){
            //return new logino(false, "User does not exist");
            return new loginObject(false,"User does not exist");
        }
        // check password
        if(!encryptedPassword.equals(credentials.get(username))){
            return new loginObject(false, "incorrect Password");
        }

        setUsername(username);
        homePanel.setNewUser(username);
        return new loginObject(true, encryptedPassword);
    }

    public static void logOut(){
        controller.getInstance().changeCard("Login");
        setUsername("");
    }

    // Create a user and encrypt the credentials
    public static loginObject createUser(String username, String password){
        if(credentials.containsKey(username)){
            // username is taken
            return new loginObject(false, "Username is Taken ");
        }
        if(password.length() < 6){
            return new loginObject(false, "Please add at least 6 characters");
        }
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        credentials.put(username, encryptedPassword);
        return new loginObject(true, encryptedPassword);
    }

    private static void setUsername(String username){
        LoginManager.username = username;
    }
}
