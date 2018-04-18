/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers;

import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.Offices;
import com.dhenton9000.birt.jpa.domain.Orders;
import com.dhenton9000.birt.jpa.service.OfficesService;
import com.dhenton9000.birt.jpa.service.OrdersService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/birt/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    
    @Autowired
    private OfficesService officesService;

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Get All Orders", notes = "lists all Orders at Classic Cars")
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{officeCode}/orders", produces = "application/json")
    @ApiOperation(value = "Get All Orders for an office", notes = "lists all Orders at a given office")
    public List<Orders> getOrdersForOffice(@PathVariable("officeCode") String officeCode) {
        Offices c = officesService.findOne(officeCode);
        if (c == null)
        {
            throw new ResourceNotFoundException("no office found for code "+officeCode);
        }
        
        return ordersService.getOrdersForOffice(officeCode);
    }
}
