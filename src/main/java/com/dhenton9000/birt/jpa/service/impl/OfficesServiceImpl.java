/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl;

import com.dhenton9000.birt.controllers.support.ResourceAlreadyExistsException;
import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.Offices;
import com.dhenton9000.birt.jpa.repositories.OfficesRepository;
import com.dhenton9000.birt.jpa.service.OfficesService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.List;
import java.util.Map;
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
        return officesRepository.findById(officeCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                "The requested officeCode [" + officeCode
                + "] does not exist."));
    }

    @Override
    public Offices saveOffice(Offices newOffice) {
        return officesRepository.save(newOffice);

    }

    @Override
    public Offices createOffice(Map input) {
        String officeCode = input.get("officeCode").toString();
        boolean okay = false;
        try {
           Offices f = this.findOne(officeCode); 
           if (f != null) {
               throw new ResourceAlreadyExistsException("Office '"+officeCode+"' already exists!");
           }
        } catch (ResourceNotFoundException re) {
            okay = true;
        }

        Offices officePayload = new Offices();
        officePayload.setAddressLine1((String) input.get("addressLine1"));
        officePayload.setAddressLine2((String) input.get("addressLine2"));
        officePayload.setOfficeCode(officeCode);
        officePayload.setCity((String) input.get("city"));
        officePayload.setPhone((String) input.get("phone"));
        officePayload.setPostalCode((String) input.get("postalCode"));
        officePayload.setTerritory((String) input.get("territory"));
        officePayload.setCountry((String) input.get("country"));
        return officesRepository.save(officePayload);

    }

}
