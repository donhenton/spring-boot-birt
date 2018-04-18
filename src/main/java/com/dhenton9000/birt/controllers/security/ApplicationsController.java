/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers.security;

import com.dhenton9000.birt.controllers.support.ResourceAlreadyExistsException;
import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.service.security.ApplicationsService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec/applications")
public class ApplicationsController {

    @Autowired
    private ApplicationsService applicationsService;

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Find All Applications", notes = "lists all Applications")
    public List<Applications> findAll() {
        return applicationsService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findOne/{id}", produces = "application/json")
    @ApiOperation(value = "Find An Application", notes = "find Application by id")
    public Applications findOne(@PathVariable("id") Integer id) {
        return applicationsService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findByName/{name}", produces = "application/json")
    @ApiOperation(value = "Find An Application", notes = "find Application by name")
    public List<Applications> findByName(@PathVariable("name") String name) {
        return applicationsService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/save", produces = "application/json")
    @ApiOperation(value = "Save An Application", notes = "Save changes to  an Application")
    public Applications save(@RequestBody Applications app) {

        Integer id = app.getId();
        Applications foundApp = applicationsService.findOne(id);
        if (foundApp == null) {
            throw new ResourceNotFoundException("cannot find application [" + id + "]");
        }

        return applicationsService.save(app);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add", produces = "application/json")
    @ApiOperation(value = "Add An Application", notes = "Add an Application")
    public Applications add(@RequestBody Applications app) {

        Integer id = app.getId();
        Applications foundApp = null;
        try {
            foundApp = applicationsService.findOne(id);
        } catch (Exception e) {
        }
        if (foundApp != null) {
            throw new ResourceAlreadyExistsException("application [" + id + "] exists already");
        }

        return applicationsService.save(app);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    @ApiOperation(value = "Delete An Application", notes = "Delete an Application")
    public void delete(@RequestBody Applications app) {

        Integer id = app.getId();
        Applications foundApp = applicationsService.findOne(id);
        if (foundApp == null) {
            throw new ResourceNotFoundException("cannot find application [" + id + "]");
        }

        applicationsService.delete(app);
    }

}
