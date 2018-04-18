/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl;

import com.dhenton9000.birt.jpa.domain.Orders;
import com.dhenton9000.birt.jpa.repositories.OrdersRepository;
import com.dhenton9000.birt.jpa.service.OrdersService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dhenton
 */
public class OrdersServiceImpl implements OrdersService {

    @PersistenceContext()
    private EntityManager entityManager;
    @Autowired
    private OrdersRepository ordersRepository;
    
    
    @Override
    public List<Orders> getAllOrders() {
        
         Iterable<Orders> items = ordersRepository.findAll();
        return EntityUtils.makeCollection(items);
    }

    @Override
    public List<Orders> getOrdersForOffice(String officeCode) {
        String qString = "select orders "
                + " from Offices offices "
                + " join  offices.employees   employees "
                + " join  employees.customers   customers "
                + " join  customers.orders   orders "
                + " where offices.officeCode = :id ";
        
        Query q = this.entityManager.createQuery(qString);
        q.setParameter("id", officeCode);
        return   q.getResultList();
    }
    
}
