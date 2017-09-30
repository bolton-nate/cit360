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
public class DepositFunds implements Handler {

    @Override
    public void handleIt(HashMap<String, Object> data) {
        if (Main.loggedIn) {
            if (Integer.parseInt(data.get("depositAmount").toString()) > 0) {
                if (data.get("accountType").equals("checking")) {
                    Main.checking += Integer.parseInt(data.get("depositAmount").toString());
                    System.out.println("$" + data.get("depositAmount").toString() + " deposited to checking");
                } else {
                    Main.savings += Integer.parseInt(data.get("depositAmount").toString());
                    System.out.println("$" + data.get("depositAmount").toString() + " deposited to savings");
                }
            }
        }
    }
    
}
