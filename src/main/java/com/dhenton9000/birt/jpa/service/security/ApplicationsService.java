/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.security;

import com.dhenton9000.birt.jpa.domain.security.Applications;
import java.util.List;

/**
 *
 * @author dhenton
 */
public interface ApplicationsService {
    
    List<Applications> findAll();
    Applications findOne(Integer id);
    List<Applications> findByName(String name);
    Applications save(Applications app);
    void delete(Applications app);
}
