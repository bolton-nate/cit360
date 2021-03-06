package com.natebolton.timeoffrequest;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by natebolton on 12/3/17.
 */

public class Employees implements Serializable {

    private Integer empId;
    private EmployeeType employeeType;
    private String lastName;
    private String firstName;
    private Set requestses = new HashSet(0);
    private Set employeeAuths = new HashSet(0);

    public Employees() {
    }


    public Employees(EmployeeType employeeType, String lastName) {
        this.employeeType = employeeType;
        this.lastName = lastName;
    }
    public Employees(EmployeeType employeeType, String lastName, String firstName, Set requestses, Set employeeAuths) {
        this.employeeType = employeeType;
        this.lastName = lastName;
        this.firstName = firstName;
        this.requestses = requestses;
        this.employeeAuths = employeeAuths;
    }

    public Integer getEmpId() {
        return this.empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
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
    public Set getRequestses() {
        return this.requestses;
    }

    public void setRequestses(Set requestses) {
        this.requestses = requestses;
    }
    public Set getEmployeeAuths() {
        return this.employeeAuths;
    }

    public void setEmployeeAuths(Set employeeAuths) {
        this.employeeAuths = employeeAuths;
    }

}
