/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg360acp;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author natebolton
 */
public class BuyStock implements Handler, Serializable {

    @Override
    public void handleIt(HashMap<String, Object> data) {
//        System.out.println(Integer.parseInt(data.get("stockCost").toString()));
//        System.out.println(Integer.parseInt(data.get("stockAmount").toString()));
        HashMap myHM = Main.getStocksOwned();
        if (data.get("accountType").equals("checking")) {
            if (Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockAmount").toString())<Main.getChecking()) {
                myHM.put(data.get("stockSymbol"), Integer.parseInt(data.get("stockAmount").toString()));
                Main.setStocksOwned(myHM);
                Main.setChecking(Main.getChecking() - Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockAmount").toString()));
                System.out.println("Stock Purchased");
            } else {
                System.out.println("You do not have enough funds in checking.");
            }
        } else {
            if (Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockCost").toString())<Main.getSavings()) {
                myHM.put(data.get("stockSymbol"), Integer.parseInt(data.get("stockAmount").toString()));
                Main.setStocksOwned(myHM);
                Main.setSavings(Main.getSavings() - Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockAmount").toString()));
                System.out.println("Stock Purchased");
            } else {
                System.out.println("You do not have enough funds in savings.");
            }         
        }
    }
    
}
