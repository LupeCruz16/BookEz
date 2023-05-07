package Controller;
import java.awt.CardLayout;
import javax.swing.*;
import java.awt.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import contentPanels.homePanel;
import contentPanels.roiPanel;
import contentPanels.accountPanel;
import contentPanels.uploadPanel;
import contentPanels.loginPanel;
import contentPanels.createUserPanel;
import Objects.LoginObject;


public class controller extends JPanel{

    private static controller instance;
    private static JFrame frame;
    private String username;
    private Map<String, String> credentials;

    JPanel cards;//create panel of cards

    //creating each classes panel to add to cards
    homePanel homePanel;
    uploadPanel photoPanel;
    roiPanel roiPanel;
    accountPanel accountPanel;
    loginPanel loginPanel;
    createUserPanel createUserPanel;

    public controller(){
        setLayout(new BorderLayout());
        setSize(300, 300);
        cards = new JPanel(new CardLayout());

        loginPanel = new loginPanel();
        createUserPanel = new createUserPanel();

        homePanel = new homePanel();
        homePanel.startSlideshow();//statting home slide show when program is opened 
        photoPanel = new uploadPanel();
        roiPanel = new roiPanel();
        accountPanel = new accountPanel();
        
        cards.add(loginPanel, "Login");
        cards.add(createUserPanel, "Create User");
        cards.add(homePanel, "Homescreen");
        cards.add(photoPanel, "Upload Photos");
        cards.add(roiPanel, "ROI Table");
        cards.add(accountPanel, "Account");
        //Export Files

        add(cards);
        setVisible(true);

    }//end of public controller 

    //dispalying homescreen panel to begin 
    private static void createAndDisplay(){
        frame = new JFrame("BookEz");

        instance = new controller();


        frame.getContentPane().add(instance);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void loginToHome() {
        controller.getInstance().changeCard("Homescreen");
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        createAndDisplay();
    }

    //to move between panels 
    public void changeCard(String card){
        CardLayout c1 = (CardLayout) (cards.getLayout());
        c1.show(cards, card);
    }

    // Login the user
    public LoginObject loginUser(String username, String password){
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        // check user
        if(!credentials.containsKey(username)){
            //return new logino(false, "User does not exist");
            return new LoginObject(false,"User does not exist");
        }
        // check password
        if(!encryptedPassword.equals(credentials.get(username))){
            return new LoginObject(false, "incorrect Password");
        }
        this.username = username;
        homePanel.setNewUser(username);
        return new LoginObject(true, encryptedPassword);
    }

    public void logOut(){
        controller.getInstance().changeCard("Login");
        frame.setSize(300, 300);
        this.username = "";
    }

    // Create a user and encrypt the credentials
    public LoginObject createUser(String username, String password){
        if(credentials.containsKey(username)){
            // username is taken
            return new LoginObject(false, "Username is Taken ");
        }
        if(password.length() < 6){
            return new LoginObject(false, "Password is not long enough. Please add at least 6 characters");
        }
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        credentials.put(username, encryptedPassword);
        return new LoginObject(true, encryptedPassword);
    }


    public static controller getInstance(){
        return instance;
    }

    public static JFrame getFrame(){
        return frame;
    }
}
