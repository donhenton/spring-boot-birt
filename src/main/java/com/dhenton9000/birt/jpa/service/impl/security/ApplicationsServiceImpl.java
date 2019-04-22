/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl.security;

import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.Orders;
import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.repositories.security.ApplicationsRepository;
import com.dhenton9000.birt.jpa.service.security.ApplicationsService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationsServiceImpl implements ApplicationsService {

    @PersistenceContext()
    private EntityManager entityManager;
    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Override
    public List<Applications> findAll() {
        Iterable<Applications> items = applicationsRepository.findAll();     
        return EntityUtils.makeCollection(items);
    }

    @Override
    public Applications findOne(Integer id) {
       // return this.checkFor404(applicationsRepository.findOne(id));
       return applicationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "The requested application [" + id
                + "] does not exist."));
    }

    @Override
    public List<Applications> findByName(String name) {
        Iterable<Applications> items = applicationsRepository.findByName(name);
        return EntityUtils.makeCollection(items);
    }

    @Override
    public Applications save(Applications app) {
          return this.checkFor404(applicationsRepository.save(app));
    }

    @Override
    public void delete(Applications app) {
          applicationsRepository.delete(app);
          
    }
    
    private  Applications checkFor404(Applications a) {
        if (a == null ) {
            throw new ResourceNotFoundException("resource not found");
        }
        return a;
    }

}
