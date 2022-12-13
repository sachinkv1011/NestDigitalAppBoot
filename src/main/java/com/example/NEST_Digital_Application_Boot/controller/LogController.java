package com.example.NEST_Digital_Application_Boot.controller;

import com.example.NEST_Digital_Application_Boot.DAO.LogDao;
import com.example.NEST_Digital_Application_Boot.model.LogModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {
    @Autowired
    private LogDao dao;


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addlog",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> addLog(@RequestBody LogModel lm){
        DateTimeFormatter dt=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String currentdate=String.valueOf(dt.format(now));
        lm.setInDate(currentdate);
        lm.setCheckOut(0);
        dao.save(lm);
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @Transactional
    @PostMapping(path = "/logout",consumes = "application/json",produces = "application/json")
    public String logOutStatus(@RequestBody LogModel lm){
        DateTimeFormatter dt=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String currentdate=String.valueOf(dt.format(now));
        lm.setOutDate(currentdate);
        dao.logOutStatus(lm.getCheckOut(),lm.getOutDate(),lm.getId());
        return "{status:success}";
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/viewCheckin")
    public List<Map<String,String>> viewAllcheckin(){

        return (List<Map<String, String>>) dao.viewlogByStatus();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllLogs")
    public List<Map<String,String>> viewAllLog(){

        return (List<Map<String, String>>) dao.viewAllLog();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewLogById",consumes = "application/json",produces = "application/json")
    public List<Map<String,String>> viewLogByEmp(@RequestBody LogModel l){
        return (List<Map<String, String>>) dao.viewlogByEmpid(l.getEmpId());
    }
}
