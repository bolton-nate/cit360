package com.natebolton.timeoffrequest;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by natebolton on 12/3/17.
 */

public class EmployeeType implements Serializable {

    private int typeId;
    private String typeTitle;

    public EmployeeType() {
    }


    public EmployeeType(int typeId) {
        this.typeId = typeId;
    }
    public EmployeeType(int typeId, String typeTitle) {
        this.typeId = typeId;
        this.typeTitle = typeTitle;
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

    @Override
    public String toString() {
        return "EmployeeType{" + "typeId=" + typeId + ", typeTitle=" + typeTitle + '}';
    }

}
