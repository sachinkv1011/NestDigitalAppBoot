package com.example.NEST_Digital_Application_Boot.controller;

import com.example.NEST_Digital_Application_Boot.DAO.EmployeeDoa;
import com.example.NEST_Digital_Application_Boot.DAO.LeaveCheckDao;
import com.example.NEST_Digital_Application_Boot.model.EmployeeModel;
import com.example.NEST_Digital_Application_Boot.model.LeaveCheckModel;
import com.example.NEST_Digital_Application_Boot.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDoa edao;
    @Autowired
    private LeaveCheckDao lcdao;


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmployee", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> AddEmployee(@RequestBody EmployeeModel e){
        List<EmployeeModel> emp = (List<EmployeeModel>) edao.UserLoginCred(e.getUsername(), e.getPassword(),e.getId());
        HashMap<String, String> hashMap = new HashMap<>();
//        edao.save(e);
//        hashMap.put("status","success");


        if(emp.size()==0){
            LocalDateTime now = LocalDateTime.now();
            edao.save(e);
            List<EmployeeModel> result = (List<EmployeeModel>) edao.UserLoginDetailsById(String.valueOf(e.getId()));
            LeaveCheckModel lc = new LeaveCheckModel();
            lc.setEmpId(String.valueOf(result.get(0).getId()));
            DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy");
            lc.setYear(d.format(now));
            lc.setCasualLeave(20);
            lc.setSickLeave(7);
            lc.setSpecialLeave(3);
            lcdao.save(lc);
            hashMap.put("status","success");
        }else{
            hashMap.put("status","failed");
        }
        return hashMap;
    }
    @CrossOrigin(origins="*")
    @PostMapping(path = "/searchEmployee",consumes = "application/json",produces = "application/json")
     List<EmployeeModel> searchEmployee(@RequestBody EmployeeModel e){
        HashMap<String,String> map=new HashMap<>();
        return (List<EmployeeModel>) edao.searchEmployee(e.getEmpName());

    }
    @CrossOrigin(origins = "*")
    @GetMapping( "/viewAllEmployee")
    List<EmployeeModel>  viewAllEmployee(){
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
    public  HashMap<String,String> employeeLogin(@RequestBody EmployeeModel e){
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
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/employeeProfile", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> getEmployeeProfile(@RequestBody EmployeeModel e) {
        return (List<EmployeeModel>) edao.GetEmployeeProfile(e.getId());
    }




}
