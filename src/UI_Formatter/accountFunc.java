package UI_Formatter;

import Controller.controller;

public class accountFunc {

    /**
     * Function will be used to logout of the users account
     */
    public static void logout(){
       controller.getInstance().changeCard("Login");
    }
}
 