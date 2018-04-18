/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers;

import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.Employees;
import com.dhenton9000.birt.jpa.domain.Orders;
import com.dhenton9000.birt.jpa.domain.SalesReport;
import com.dhenton9000.birt.jpa.service.EmployeesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/birt/employees/")
public class EmployeesController {

    @Autowired
    
    private EmployeesService employeesService;
    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Get All Customers", notes = "lists all Customers at Classic Cars")
    public List<Employees> getAllEmployees() {
        return employeesService.getAllEmployees();
    }
 
    @RequestMapping(method = RequestMethod.GET, path = "/{employeeId}/orders", produces = "application/json")
    @ApiOperation(value = "Get All Customers", notes = "lists all Orders for an Employee")
    public List<Orders> getOrdersForEmployee(@PathVariable("employeeId") Integer employeeId) {
        return employeesService.getOrdersForEmployee(employeeId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/salesdata", produces = "application/json")
    @ApiOperation(value = "Get SalesData", notes = "lists all Sales Data")
    public List<SalesReport> getSalesData() {
        return employeesService.getSalesData();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/office/{officeCode}", produces = "application/json")
    @ApiOperation(value = "Get Employees For An Office", notes = "lists Employees in A Given Office")
    public List<Employees> getEmployeesForOffice(@PathVariable("officeCode") String officeCode) {
        return employeesService.getEmployeesForOffice(officeCode);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{employeeId}", produces = "application/json")
    @ApiOperation(value = "Find Employee", notes = "Find a Single Employee. <b>Negative ids yield 500 error</b>")
    
    public Employees getById(@ApiParam(name = "employeeId",required = true,value = "the employeeId negative to see 500 error")
           @PathVariable("employeeId") Integer employeeId) {
        
        Employees e =  employeesService.getById(employeeId);
        
        if (e == null)
        {
            throw new ResourceNotFoundException("cannot find employee "+employeeId);
        }
        return e;

    }

}
