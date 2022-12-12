package com.example.NEST_Digital_Application_Boot.controller;

import com.example.NEST_Digital_Application_Boot.DAO.SecurityDao;
import com.example.NEST_Digital_Application_Boot.model.EmployeeModel;
import com.example.NEST_Digital_Application_Boot.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private SecurityDao sdao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addSecurity",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> addSecurity(@RequestBody SecurityModel s){
        HashMap<String,String> map=new HashMap<>();
        sdao.save(s);
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins="*")
    @PostMapping(path = "/searchSecurity",consumes = "application/json",produces = "application/json")
    List<EmployeeModel> searchSecurity(@RequestBody SecurityModel s){
        HashMap<String,String> map=new HashMap<>();
        return (List<EmployeeModel>) sdao.searchSecurity(s.getName());

    }
}
