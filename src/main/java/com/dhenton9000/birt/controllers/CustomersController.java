package com.dhenton9000.birt.controllers;

import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.BasicCustomer;
import com.dhenton9000.birt.jpa.domain.Customers;
import com.dhenton9000.birt.jpa.domain.Orders;
import com.dhenton9000.birt.jpa.service.CustomersService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author dhenton
 */

@RestController
@RequestMapping("/birt/customers/")
public class CustomersController {

    @Autowired
    private CustomersService customersService;
   
    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Get All Customers", notes = "lists all Customers at Classic Cars")

    public List<Customers> getAllCustomers() {
        return this.customersService.getAllCustomers();

    }
    
    
    @RequestMapping(method = RequestMethod.GET, path = "/basicCustomer", produces = "application/json")
    @ApiOperation(value = "Get All Customers With DTO", notes = "lists all Customers at Classic Cars demonstrates DTO")

    public List<BasicCustomer> getBasicCustomers() {
        return this.customersService.getBasicCustomerList();

    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/{customerId}/orders", produces = "application/json")
    @ApiOperation(value = "Get Orders For a Customers", notes = "lists all Orders for a given customer at Classic Cars")
    
    public List<Orders> getAllOrdersForCustomer(@PathVariable("customerId") Integer customerId) {
        
        Customers c = customersService.findCustomer(customerId);
        if (c == null)
        {
            throw new ResourceNotFoundException("could not find customerNumber '" + customerId
                    + "'");
        }
        
        return this.customersService.getCustomerOrders(customerId);

    }
}
