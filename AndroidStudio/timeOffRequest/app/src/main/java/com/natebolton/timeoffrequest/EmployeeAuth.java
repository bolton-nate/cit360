package com.natebolton.timeoffrequest;

import java.io.Serializable;

/**
 * Created by natebolton on 12/3/17.
 */

public class EmployeeAuth implements Serializable {

    private Integer authId;
    private Integer emp_id;
    private String username;
    private String password;

    public EmployeeAuth() {
    }

    public EmployeeAuth(Integer emp_id, String username, String password) {
        this.emp_id = emp_id;
        this.username = username;
        this.password = password;
    }

    public Integer getAuthId() {
        return this.authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmployeeAuth{" + "authId=" + authId + ", emp_id=" + emp_id + ", username=" + username + ", password=" + password + '}';
    }
}
