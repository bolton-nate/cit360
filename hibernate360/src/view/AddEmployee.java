/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import hibernate360.Hibernate360;
import model.Employees;

/**
 *
 * @author natebolton
 */

public class AddEmployee extends View {

    public AddEmployee() {
        super("Please enter the employee first name: ");
    }

    @Override
    public boolean doAction(String firstName) {
        if ("Q".equals(firstName)) {
            return true;
        }
        this.displayMessage = "Please enter the employee last name: ";
        String lastName = this.getInput();
        if ("Q".equals(lastName)) {
            return true;
        }
        this.displayMessage = "Please enter the employee type\n(1: admin\n2: hourly\n3: salary)";
        String empType = this.getInput();
        if ("Q".equals(lastName)) {
            return true;
        }        
        Hibernate360 hb360 = new Hibernate360();

        Employees employee = hb360.addEmployees(lastName,firstName,Integer.parseInt(empType));
        if (employee != null) {
            this.console.println("Employee Added.\n\n");
        } else {
            this.console.println("Error, please try again later.\n\n");
        }
        
        return true;
    }
    
}
