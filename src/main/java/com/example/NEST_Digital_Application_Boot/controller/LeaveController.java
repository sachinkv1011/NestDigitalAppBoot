package com.example.NEST_Digital_Application_Boot.controller;

import com.example.NEST_Digital_Application_Boot.DAO.LeaveCheckDao;
import com.example.NEST_Digital_Application_Boot.DAO.LeaveDao;
import com.example.NEST_Digital_Application_Boot.model.LeaveCheckModel;
import com.example.NEST_Digital_Application_Boot.model.LeaveModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    @Autowired
    private LeaveDao ldao;

    @Autowired
    private LeaveCheckDao lcdao;

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
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updateLeaves", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UpdateLeaves(@RequestBody LeaveModel lv){
        lcdao.GetLeaveUpdates(lv.getId(), String.valueOf(lv.getStatus()));
        String leaveType = lv.getType();
        LocalDate dateBefore = LocalDate.parse(lv.getApplyDate());
        LocalDate dateAfter = LocalDate.parse(lv.getLeaveDate());
        int daysOfLeave = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter)+1;
        List<LeaveCheckModel> l1 = (List<LeaveCheckModel>) lcdao.GetLeaveDetails(lv.getEmp_id());
        System.out.println(l1.get(0).getCasualLeave()-daysOfLeave);
        if(leaveType.equals("casualLeave")){
            lcdao.UpdateLeave(lv.getEmp_id(),(l1.get(0).getCasualLeave()-daysOfLeave),l1.get(0).getSickLeave(),l1.get(0).getSpecialLeave());
        } else if (leaveType.equals("sickLeave")) {
            lcdao.UpdateLeave(lv.getEmp_id(),l1.get(0).getCasualLeave(),(l1.get(0).getSickLeave()-daysOfLeave),l1.get(0).getSpecialLeave());
        }else if (leaveType.equals("specialLeave")){
            l1.get(0).setSpecialLeave(l1.get(0).getSickLeave()-daysOfLeave);
            lcdao.UpdateLeave(lv.getEmp_id(),l1.get(0).getCasualLeave(),l1.get(0).getSickLeave(),(l1.get(0).getSickLeave()-daysOfLeave));
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status","success");
        return hashMap;
    }




}
