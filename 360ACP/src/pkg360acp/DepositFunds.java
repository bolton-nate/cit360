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
        if (Main.isLoggedIn()) {
            if (Integer.parseInt(data.get("depositAmount").toString()) > 0) {
                if (data.get("accountType").equals("checking")) {
                    Main.setChecking(Main.getChecking() + Integer.parseInt(data.get("depositAmount").toString()));
                    System.out.println("$" + data.get("depositAmount").toString() + " deposited to checking");
                } else {
                    Main.setSavings(Main.getSavings() + Integer.parseInt(data.get("depositAmount").toString()));
                    System.out.println("$" + data.get("depositAmount").toString() + " deposited to savings");
                }
            }
        }
    }
    
}
