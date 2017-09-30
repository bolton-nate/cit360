/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg360acp;

import java.util.HashMap;

/**
 *
 * @author natebolton
 */
public class Login implements Handler {

    @Override
    public void handleIt(HashMap<String, Object> data) {

        if (data.get("username").equals("admin") && data.get("password").equals("password")) {
            System.out.println("Welcom, Admin.  You are Logged in.");
            Main.loggedIn = true;
        } else {
            System.out.println("I'm sorry, you are not admin/password.  Please try again later.");
            System.exit(0);
        }
    }
    
}
