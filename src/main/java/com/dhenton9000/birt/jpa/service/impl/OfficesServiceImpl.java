/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl;

import com.dhenton9000.birt.jpa.domain.Offices;
import com.dhenton9000.birt.jpa.repositories.OfficesRepository;
import com.dhenton9000.birt.jpa.service.OfficesService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficesServiceImpl implements OfficesService {

    @PersistenceContext()
    private EntityManager entityManager;
    @Autowired
    private OfficesRepository officesRepository;

    @Override
    public List<Offices> getAllOffices() {
        Iterable<Offices> items = officesRepository.findAll();
        return EntityUtils.makeCollection(items);
    }

    @Override
    public Offices findOne(String officeCode) {
         return officesRepository.findOne(officeCode);
    }

}
