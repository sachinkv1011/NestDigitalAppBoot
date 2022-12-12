package com.example.NEST_Digital_Application_Boot.DAO;

import com.example.NEST_Digital_Application_Boot.model.EmployeeModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDoa extends CrudRepository<EmployeeModel,Integer> {

    @Query(value = "SELECT `id`, `emp_designation`, `emp_email`, `emp_name`, `emp_phone`, `password`, `username` FROM `employee` WHERE `emp_name`=:empName",nativeQuery = true)
    List<EmployeeModel> searchEmployee(@Param("empName")String empName);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM `employee` WHERE `id`=:id",nativeQuery = true)
    void deleteEmployee(@Param("id") Integer id);
}
