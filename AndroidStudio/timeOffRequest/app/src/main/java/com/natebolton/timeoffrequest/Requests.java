package com.natebolton.timeoffrequest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by natebolton on 12/5/17.
 */

public class Requests implements Serializable {

    private Integer requestId;
    private Date timestamp;
    private Integer emp_id;
    private Date startDate;
    private Date returnDate;
    private String reason;
    private int daysRequested;

    public Requests() {
    }

    public Requests(Integer emp_id, Date startDate, Date returnDate, String reason, int daysRequested) {
        this.emp_id = emp_id;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.reason = reason;
        this.daysRequested = daysRequested;
        this.timestamp = Calendar.getInstance().getTime();
    }

    public Requests(Integer emp_id, Date startDate, Date returnDate, String reason, int daysRequested, Date timestamp) {
        this.emp_id = emp_id;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.reason = reason;
        this.daysRequested = daysRequested;
        this.timestamp = timestamp;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getDaysRequested() {
        return daysRequested;
    }

    public void setDaysRequested(int daysRequested) {
        this.daysRequested = daysRequested;
    }

    @Override
    public String toString() {
        return "Requests{" + "requestId=" + requestId + ", timestamp=" + timestamp + ", emp_id=" + emp_id + ", startDate=" + startDate + ", returnDate=" + returnDate + ", reason=" + reason + ", daysRequested=" + daysRequested + '}';
    }


}
