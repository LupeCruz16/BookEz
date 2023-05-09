package Controller;
import java.awt.CardLayout;
import javax.swing.*;
import java.awt.*;

import contentPanels.homePanel;
import contentPanels.roiPanel;
import contentPanels.accountPanel;
import contentPanels.uploadPanel;
import contentPanels.loginPanel;
import contentPanels.createUserPanel;

public class controller extends JPanel{

    private static controller instance;
    private static JFrame frame;

    JPanel cards;//create panel of cards

    //creating each classes panel to add to cards
    homePanel homePanel;
    uploadPanel photoPanel;
    roiPanel roiPanel;
    accountPanel accountPanel;
    loginPanel loginPanel;
    createUserPanel createUserPanel;

    public controller(){
        //credentials = new HashMap<>();
        setLayout(new BorderLayout());
        setSize(300, 300);
        cards = new JPanel(new CardLayout());

        loginPanel = new loginPanel();
        createUserPanel = new createUserPanel();
        homePanel = new homePanel();
        homePanel.startSlideshow();//statting home slide show when the user logs in 
        photoPanel = new uploadPanel();
        roiPanel = new roiPanel();
        accountPanel = new accountPanel();
        
        cards.add(loginPanel, "Login");
        cards.add(createUserPanel, "Create User");
        cards.add(homePanel, "Homescreen");
        cards.add(photoPanel, "Upload Photos");
        cards.add(roiPanel, "ROI Table");
        cards.add(accountPanel, "Account");//needs to be completed
        //future additon: export Files

        add(cards);
        setVisible(true);

    } 

    /**
     * Creating and displaying the slides
     */
    private static void createAndDisplay(){
        frame = new JFrame("BookEz");

        instance = new controller();

        frame.getContentPane().add(instance);
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        createAndDisplay();
    }

    /**
     * Changes current visible panel
     * @param card name of panel to be changed to 
     */
    public void changeCard(String card){
        CardLayout c1 = (CardLayout) (cards.getLayout());
        c1.show(cards, card);
    }

    public static controller getInstance(){
        return instance;
    }

    public static JFrame getFrame(){
        return frame;
    }
}
