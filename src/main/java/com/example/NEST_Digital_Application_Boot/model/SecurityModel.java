package com.example.NEST_Digital_Application_Boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "security")
public class SecurityModel {
    @Id
    @GeneratedValue()
    private int id;
    private String name;
    private String address;
    private String username;
    private String password;

    public SecurityModel() {
    }

    public SecurityModel(int id, String name, String address, String username, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
