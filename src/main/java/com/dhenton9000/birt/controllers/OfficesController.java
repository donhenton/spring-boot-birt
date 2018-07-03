/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers;

import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.controllers.support.ResourceAlreadyExistsException;
import com.dhenton9000.birt.jpa.domain.Offices;
import com.dhenton9000.birt.jpa.service.OfficesService;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/birt/offices")
public class OfficesController {
    
    private static final Logger LOG = LoggerFactory.getLogger(OfficesController.class);
    @Autowired
    private OfficesService officesService;

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Get All Offices", notes = "lists all Offices at Classic Cars")
    public List<Offices> getAllOffices() {
        return officesService.getAllOffices();
    }
    
    
    @RequestMapping(method = RequestMethod.GET, path = "/{officeCode}", produces = "application/json")
    @ApiOperation(value = "Find a single office", notes = "find a single office")
    public Offices findOne(@PathVariable("officeCode") String officeCode) {
        Offices o =  officesService.findOne(officeCode);
        if(o == null)
        {
            throw new ResourceNotFoundException("cannot find office for code "+officeCode);
        }
        return o;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/saveOffice", 
            produces = "application/json",consumes="application/json")
    @ApiOperation(value = "Save an office", notes = "Save an office and return the persisted office with an id")
    public Offices saveOffice(@RequestBody Offices newOffice) {
        String officeCode = newOffice.getOfficeCode();
        LOG.info("office Code "+newOffice.toString());
        Offices o =  officesService.findOne(officeCode);
        if(o != null)
        {
            throw new ResourceAlreadyExistsException("office already exists "+officeCode);
        }
        return officesService.saveOffice(newOffice);
    }
    
    

}
