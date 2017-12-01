package model;
// Generated Nov 27, 2017 8:07:13 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * EmployeeType generated by hbm2java
 */
public class EmployeeType  implements java.io.Serializable {


     private int typeId;
     private String typeTitle;
     private Set employeeses = new HashSet(0);

    public EmployeeType() {
    }

	
    public EmployeeType(int typeId) {
        this.typeId = typeId;
    }
    public EmployeeType(int typeId, String typeTitle, Set employeeses) {
       this.typeId = typeId;
       this.typeTitle = typeTitle;
       this.employeeses = employeeses;
    }
   
    public int getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public String getTypeTitle() {
        return this.typeTitle;
    }
    
    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }
    public Set getEmployeeses() {
        return this.employeeses;
    }
    
    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }




}


