package com.example.NEST_Digital_Application_Boot.controller;

import com.example.NEST_Digital_Application_Boot.DAO.LeaveDao;
import com.example.NEST_Digital_Application_Boot.model.LeaveModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    @Autowired
    private LeaveDao ldao;
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addleave",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> addLeave(@RequestBody LeaveModel l){
        DateTimeFormatter dt=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String currentdate=String.valueOf((dt.format(now)));
        l.setApplyDate(currentdate);
        System.out.println(l.getApplyDate());
        System.out.println(l.getId());
        ldao.save(l);
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllLeaves")
    public List<Map<String ,String>> viewallleaves(){
        return (List<Map<String, String>>) ldao.viewAllLeaves();
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewLeavesByEmpid",consumes = "application/json",produces = "application/json")
    public  List<Map<String,String>> viewLeavesById(@RequestBody LeaveModel l){
        return (List<Map<String, String>>) ldao.viewLeaveById(l.getEmp_id());
    }
    @Transactional
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updateStatus",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> updateStatus(@RequestBody LeaveModel l){
        ldao.updateById(l.getStatus(),l.getId());
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
    }



}
