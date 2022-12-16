package com.example.NEST_Digital_Application_Boot.DAO;

import com.example.NEST_Digital_Application_Boot.model.LeaveModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LeaveDao extends CrudRepository<LeaveModel,Integer> {

 @Query(value = "SELECT l.`id`, l.`apply_date`, l.`description`, l.`emp_id`, l.`leave_date`, l.`status`, e.emp_name,e.emp_designation FROM `leave_total` l JOIN employee e ON l.emp_id=e.id WHERE status=0",nativeQuery = true)
 List<Map<String,String>> viewAllLeaves();

 @Query(value = "SELECT l.`id`, l.`apply_date`, l.`description`, l.`emp_id`, l.`leave_date`, l.`status`, l.`type`,e.emp_name,E.emp_designation FROM `leave_total` l JOIN employee e ON L.emp_id=e.id WHERE l.emp_id=:emp_id",nativeQuery = true)
 List<Map<String,String>> viewLeaveById(@Param("emp_id")Integer emp_id);

 @Transactional
 @Modifying

 @Query(value = "UPDATE `leave_total` SET status=:status WHERE id=:id",nativeQuery = true)
 void updateById(@Param("status") Integer status,@Param("id") Integer id );
}
