/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import hibernate360.Hibernate360;

/**
 *
 * @author natebolton
 */
public class Login extends View {
    
    public Login() {
        super("\n"
        + "\n======================================="
        + "\n| Please Login                          |"
        + "\n======================================="
        + "\nPlease Enter Your Username"
        + "\n=======================================");
    }
    
    @Override
    public boolean doAction(String value) {
        String uname = value;
        this.displayMessage = "\n"
        + "\n======================================="
        + "\nPlease Enter Your Password"
        + "\n=======================================";
        String pword = this.getInput();
        if (Hibernate360.authenticateUser(uname, pword)) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.display();
        } else {
            this.console.println("Error, wrong username or password.");
            this.displayMessage = "\n"
        + "\n======================================="
        + "\n| Please Login                          |"
        + "\n======================================="
        + "\nPlease Enter Your Username"
        + "\n=======================================";
        }
        
        return false;
    }

}