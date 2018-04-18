/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.repositories;

import com.dhenton9000.birt.jpa.domain.Offices;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author dhenton
 */
public interface OfficesRepository extends CrudRepository<Offices,String>{
    
}
