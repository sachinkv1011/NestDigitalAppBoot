package com.example.NEST_Digital_Application_Boot.DAO;

import com.example.NEST_Digital_Application_Boot.model.SecurityModel;
import org.springframework.data.repository.CrudRepository;

public interface SecurityDao extends CrudRepository<SecurityModel,Integer> {
}
