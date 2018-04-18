/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl;

import com.dhenton9000.birt.jpa.domain.BasicCustomer;
import com.dhenton9000.birt.jpa.domain.Customers;
import com.dhenton9000.birt.jpa.domain.Orders;
import com.dhenton9000.birt.jpa.repositories.CustomersRepository;
import com.dhenton9000.birt.jpa.repositories.OrdersRepository;
import com.dhenton9000.birt.jpa.service.CustomersService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersServiceImpl implements CustomersService {

    @PersistenceContext()
    private EntityManager entityManager;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> getCustomerOrders(Integer id) {
        Iterable<Orders> items = getOrdersRepository().findAllOrdersForCustomer(id);
        return EntityUtils.makeCollection(items);
    }

    @Override
    public List<Customers> getAllCustomers() {

        Iterable<Customers> items = getCustomersRepository().findAll();
        return EntityUtils.makeCollection(items);
    }

    /**
     * @return the customersRepository
     */
    public CustomersRepository getCustomersRepository() {
        return customersRepository;
    }

    /**
     * @return the ordersRepository
     */
    public OrdersRepository getOrdersRepository() {
        return ordersRepository;
    }

    @Override
    public Customers findCustomer(Integer customerId) {
        return getCustomersRepository().findOne(customerId);
    }

    //example of a DTO
    
    @Override
    public List<BasicCustomer> getBasicCustomerList() {

        List<BasicCustomer> list = new ArrayList<BasicCustomer>();
        String qString = "select  new com.dhenton9000.birt."
                + "jpa.domain.BasicCustomer(c.customerName,"
                + "c.phone,"
                + "c.addressLine1,"
                + "c.addressLine2,"
                + "c.city,"
                + "c.stateName,"
                + "c.country,"
                + "c.postalCode,"
                + "c.customerNumber)"
                + " from  Customers c";

        Query q = entityManager.createQuery(qString);
        list = q.getResultList();

        return list;
    }

}
