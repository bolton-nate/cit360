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
public class BuyStock implements Handler {

    @Override
    public void handleIt(HashMap<String, Object> data) {
//        System.out.println(Integer.parseInt(data.get("stockCost").toString()));
//        System.out.println(Integer.parseInt(data.get("stockAmount").toString()));
        if (data.get("accountType").equals("checking")) {
            if (Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockAmount").toString())<Main.checking) {
                Main.stocksOwned.put(data.get("stockSymbol"), Integer.parseInt(data.get("stockAmount").toString()));
                Main.checking -= Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockAmount").toString());
                System.out.println("Stock Purchased");
            } else {
                System.out.println("You do not have enough funds in checking.");
            }
        } else {
            if (Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockCost").toString())<Main.savings) {
                Main.stocksOwned.put(data.get("stockSymbol"), Integer.parseInt(data.get("stockAmount").toString()));
                Main.savings -= Integer.parseInt(data.get("stockCost").toString())*Integer.parseInt(data.get("stockAmount").toString());
                System.out.println("Stock Purchased");
            } else {
                System.out.println("You do not have enough funds in savings.");
            }         
        }
    }
    
}
