package com.example.NEST_Digital_Application_Boot.DAO;

import com.example.NEST_Digital_Application_Boot.model.EmployeeModel;
import com.example.NEST_Digital_Application_Boot.model.SecurityModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityDao extends CrudRepository<SecurityModel,Integer> {

    @Query(value = "SELECT * FROM `security` WHERE `name`=:name",nativeQuery = true)
    List<SecurityModel> searchSecurity(@Param("name")String name);
    @Modifying
    @Transactional
    @Query(value="DELETE FROM `security` WHERE  `id`=:id",nativeQuery = true)
    void deleteSecurity(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `address`, `name`, `password`, `username` FROM `security` WHERE `username`=:username  AND `password`=:password",nativeQuery = true)
    List<SecurityModel> securityLogin(@Param("username")String username,@Param("password") String password);
}
