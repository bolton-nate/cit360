package model;
// Generated Oct 2, 2017 5:20:01 PM by Hibernate Tools 4.3.1



/**
 * Employees generated by hbm2java
 */
public class Employees  implements java.io.Serializable {


     private Integer empId;
     private String lastName;
     private String firstName;
     private int empType;

    public Employees() {
    }

	
    public Employees(String lastName, int empType) {
        this.lastName = lastName;
        this.empType = empType;
    }
    public Employees(String lastName, String firstName, int empType) {
       this.lastName = lastName;
       this.firstName = firstName;
       this.empType = empType;
    }
   
    public Integer getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getEmpType() {
        return this.empType;
    }
    
    public void setEmpType(int empType) {
        this.empType = empType;
    }




}


