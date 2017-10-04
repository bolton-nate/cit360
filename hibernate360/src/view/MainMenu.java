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
public class MainMenu extends View {

    public MainMenu() {
        super("\n"
        + "\n======================================="
        + "\n| Main Menu                           |"
        + "\n======================================="
        + "\nV - (V)iew Employee List"
        + "\nA - (A)dd Employee"
        + "\nR - (R)emove Employee"
        + "\nQ - (Q)uit Program"
        + "\n=======================================");
    }
    
    @Override
    public boolean doAction(String value) {
        value = value.toUpperCase();
        
        switch (value) {
            case "V":
                this.viewEmployees();
                break;
            case "A":
                this.addEmployee();
                break;
            case "R":
                this.removeEmployee();
                break;
            case "Q":
                this.console.println("Goodbye.");
                System.exit(0);
            default:
                this.console.println("\n*** Invalid selection *** Try again ***");
                break;
        }
        return false;
    }

    private void viewEmployees() {
        this.console.println("Here is the current List of Employees:\n");
        Hibernate360 hb360 = new Hibernate360();
        hb360.listEmployees();
    }

    private void addEmployee() {
        AddEmployee addEmployee = new AddEmployee();
        addEmployee.display();
    }

    private void removeEmployee() {
        this.viewEmployees();
        RemoveEmployee removeEmployee = new RemoveEmployee();
        removeEmployee.display();
    }
    
}
