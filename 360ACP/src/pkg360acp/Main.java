/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg360acp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author natebolton
 */
public class Main implements Serializable {

    /**
     * @param args the command line arguments
     */
    private static boolean loggedIn = false;
    private static int savings = 0;
    private static int checking = 0;
    private static HashMap stocksOwned = new HashMap();
    
    public void main() {

        
        //create app controller and establish feed it some view classes
        ApplicationController appController = new ApplicationController();
        appController.mapCommand("login", new Login());
        appController.mapCommand("deposit", new DepositFunds());
        appController.mapCommand("buyStock", new BuyStock());
        
        HashMap handlerData = new HashMap();
        //ask the user to log in.
        while (!this.loggedIn) {
            System.out.println("Welcome to Fake Bank of CIT360\n\nPlease Log in.\n\n");
            handlerData.put("username", Main.getInput("Username:  "));
            handlerData.put("password", Main.getInput("Password:  "));
            appController.handleRequest("login", handlerData);
        }
        while (this.loggedIn) {
            handlerData.clear();
            System.out.println("\nYour Checking Balance is:  $" + this.checking);
            System.out.println("\nYour Savings Balance is:  $" + this.savings);
            System.out.println("\nYour owned stocks are:\n");
            Iterator iterator = this.stocksOwned.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry value = (Map.Entry) iterator.next();
                System.out.println("Stock: "+ value.getKey() + " & Amount: " + value.getValue());
            } 
            String choice = Main.getInput("\n\nWhat would you like to do?\n\n1) Deposit Funds\n2) Buy Stock\n3) Exit");
            switch (choice) {
                case "1":
                {
                    handlerData.put("depositAmount", Integer.parseInt(Main.getInput("How much will you deposit?")));
                    handlerData.put("accountType", Main.getInput("Would like to deposit into \"checking\" or \"savings\"?"));
                    appController.handleRequest("deposit", handlerData);
                    break;
                }
                case "2":
                {
                    handlerData.put("stockSymbol", Main.getInput("What is the symbol of the stock you wish to buy?"));
                    handlerData.put("stockCost", Main.getInput("What is the price per share of the stock?"));
                    handlerData.put("stockAmount", Main.getInput("How many shares do you wish to buy?"));
                    handlerData.put("accountType", Main.getInput("From which account will purchase from (checking or savings)?"));
                    appController.handleRequest("buyStock", handlerData);
                    break;
                }
                case "3":
                    System.out.println("\n*** Come again soon! ***");
                    System.exit(0);
                default:
                    System.out.println("\n*** Invalid selection *** Goodbye ***");
                    System.exit(0);
            }
        }
    }
    
    public static String getInput(String promptMessage) {

        Scanner keyboard = new Scanner(System.in); 
        String value = "";
        boolean valid = false;

        while (!valid) {
            System.out.println(promptMessage);

            value = keyboard.nextLine();
            value = value.trim();

            if (value.length() < 1) {
                System.out.println("\nInvalid value: value can not be blank");
                continue;
            }
            break;
        }
    return value;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        Main.loggedIn = loggedIn;
    }

    public static int getSavings() {
        return savings;
    }

    public static void setSavings(int savings) {
        Main.savings = savings;
    }

    public static int getChecking() {
        return checking;
    }

    public static void setChecking(int checking) {
        Main.checking = checking;
    }

    public static HashMap getStocksOwned() {
        return stocksOwned;
    }

    public static void setStocksOwned(HashMap stocksOwned) {
        Main.stocksOwned = stocksOwned;
    }
    
}
