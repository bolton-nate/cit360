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
public class RemoveEmployee extends View {

    public RemoveEmployee() {
        super("Please select the ID of the employee you wish to delete:");
    }
    @Override
    public boolean doAction(String value) {
        if ("Q".equals(value)) {
            return true;
        }
        Integer intValue = Integer.parseInt(value);
        Hibernate360 hb360 = new Hibernate360();
        hb360.removeEmployee(intValue);
        this.console.println("\n\nEmployee removal attempted.  Please view employees to verify removal.");
        
        return true;
    }
    
}
