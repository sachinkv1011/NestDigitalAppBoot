package com.example.NEST_Digital_Application_Boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "logdetails")
public class LogModel {
    @Id
    @GeneratedValue
    private int id;
    private int empId;
    private String checkIn;
    private int checkOut;
    private String inDate;
    private String outDate;

    public LogModel() {
    }

    public LogModel(int id, int empId, String checkIn, int checkOut, String inDate, String outDate) {
        this.id = id;
        this.empId = empId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
}
