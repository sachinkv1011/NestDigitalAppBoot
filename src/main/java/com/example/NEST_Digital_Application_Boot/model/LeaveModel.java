package com.example.NEST_Digital_Application_Boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leaveTotal")
public class LeaveModel {
    @Id
    @GeneratedValue()
    private int id;
    private int emp_id;
    private int status;
    private String type;
    private String description;
    private String applyDate;
    private String leaveDate;

    public LeaveModel() {
    }

    public LeaveModel(int id, int emp_id, int status, String type, String description, String applyDate, String leaveDate) {
        this.id = id;
        this.emp_id = emp_id;
        this.status = status;
        this.type = type;
        this.description = description;
        this.applyDate = applyDate;
        this.leaveDate = leaveDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }
}
