package com.example.NEST_Digital_Application_Boot.controller;

import com.example.NEST_Digital_Application_Boot.DAO.EmployeeDoa;
import com.example.NEST_Digital_Application_Boot.model.EmployeeModel;
import com.example.NEST_Digital_Application_Boot.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDoa edao;


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmployee",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> addEmployee(@RequestBody EmployeeModel e){
        HashMap<String,String> map=new HashMap<>();
        edao.save(e);
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins="*")
    @PostMapping(path = "/searchEmployee",consumes = "application/json",produces = "application/json")
     List<EmployeeModel> searchEmployee(@RequestBody EmployeeModel e){
        HashMap<String,String> map=new HashMap<>();
        return (List<EmployeeModel>) edao.searchEmployee(e.getEmpName());

    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/viewAllEmployee")
    List<EmployeeModel>  viewAllEmployee(@RequestBody EmployeeModel e){
        return (List<EmployeeModel>) edao.findAll();
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path="/deleteEmployee",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> DeleteEmployee(@RequestBody EmployeeModel e) {
        String id = String.valueOf(e.getId());
        edao.deleteEmployee(e.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/employeeLogin",consumes = "application/json",produces = "application/json")
    public  HashMap<String,String> securityLogin(@RequestBody EmployeeModel e){
        HashMap<String,String> map=new HashMap<>();
        List<EmployeeModel> details= (List<EmployeeModel>) edao.employeeLogin(e.getUsername(),e.getPassword());
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




}
