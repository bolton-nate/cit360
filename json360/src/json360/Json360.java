/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json360;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import model.Employees;
/**
 *
 * @author natebolton
 */
public class Json360 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Json360 main = new Json360();
        main.toJson();
        main.fromJson();
    }
    
    private void toJson() {
        System.out.println("\n*****************\nTo JSON File\n\n");
        ObjectMapper mapper = new ObjectMapper();

        Employees emp = createEmployee();

        try {
                // Convert object to JSON string and save into a file directly
                mapper.writeValue(new File("employee.json"), emp);

                // Convert object to JSON string
                String jsonInString = mapper.writeValueAsString(emp);
                System.out.println(jsonInString);

                // Convert object to JSON string and pretty print
                jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
                System.out.println(jsonInString);

        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    private void fromJson() {
        System.out.println("\n*****************\nFrom JSON File\n\n");
        ObjectMapper mapper = new ObjectMapper();

        try {

                // Convert JSON string from file to Object
                Employees emp = mapper.readValue(new File("employee.json"), Employees.class);
                System.out.println(emp);
                System.out.println(emp.getEmpId());

                //Pretty print
                String prettyEmp1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
                System.out.println(prettyEmp1);

        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    private Employees createEmployee() {

        Employees emp = new Employees();

        emp.setEmpId(1001);
        emp.setFirstName("FName1");
        emp.setLastName("LName1");
        emp.setEmpType(1);

        return emp;

    }
}
