/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.repositories.security;


import com.dhenton9000.birt.jpa.domain.security.Applications;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dhenton
 */
@Repository
public interface ApplicationsRepository extends CrudRepository<Applications,Integer>{
    
    
    
     @Query("SELECT a FROM Applications a WHERE a.applicationName = :applicationName")
    List<Applications> findByName(@Param("applicationName") String applicationName);
    
}
