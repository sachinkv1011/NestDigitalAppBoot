package com.example.NEST_Digital_Application_Boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LeaveCounter")
public class LeaveCheckModel {
    @Id
    @GeneratedValue
    private int id;
    private String empId;
    private int sickLeave;
    private int casualLeave;
    private String year;
    private int specialLeave;

    public LeaveCheckModel() {
    }

    public LeaveCheckModel(int id, String empId, int sickLeave, int casualLeave, String year, int specialLeave) {
        this.id = id;
        this.empId = empId;
        this.sickLeave = sickLeave;
        this.casualLeave = casualLeave;
        this.year = year;
        this.specialLeave = specialLeave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    public int getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(int casualLeave) {
        this.casualLeave = casualLeave;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSpecialLeave() {
        return specialLeave;
    }

    public void setSpecialLeave(int specialLeave) {
        this.specialLeave = specialLeave;
    }
}
