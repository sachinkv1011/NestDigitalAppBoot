package com.example.NEST_Digital_Application_Boot.controller;

import com.example.NEST_Digital_Application_Boot.DAO.SecurityDao;
import com.example.NEST_Digital_Application_Boot.model.EmployeeModel;
import com.example.NEST_Digital_Application_Boot.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
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
    List<SecurityModel> searchSecurity(@RequestBody SecurityModel s){
        HashMap<String,String> map=new HashMap<>();
        return (List<SecurityModel>) sdao.searchSecurity(s.getName());

    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewAllSecurity")
    List<SecurityModel>  viewAllSecurity(){
        return (List<SecurityModel>) sdao.findAll();
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path="/deleteSecurity",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> DeleteEmployee(@RequestBody SecurityModel s) {
        String id = String.valueOf(s.getId());
        sdao.deleteSecurity(s.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/securityLogin",consumes = "application/json",produces = "application/json")
     public  HashMap<String,String> securityLogin(@RequestBody SecurityModel s){
        HashMap<String,String> map=new HashMap<>();
        List<SecurityModel> details= (List<SecurityModel>) sdao.securityLogin(s.getUsername(),s.getPassword());
        if(details.size()==0){
            map.put("status","failed");
        }
        else{
            map.put("status","success");
            String id = String.valueOf(details.get(0).getId());
            map.put("userId",id);
        }
        return map;

    }
    @PostMapping(path = "/securityProfile", consumes = "application/json", produces = "application/json")
    public List<SecurityModel> getSecurityProfile(@RequestBody SecurityModel s){
        System.out.println(s.getId());
        return (List<SecurityModel>) sdao.GetSecurityProfile(s.getId());}
}
