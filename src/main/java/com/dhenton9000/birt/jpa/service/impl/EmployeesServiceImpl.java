/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl;

import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.Employees;
import com.dhenton9000.birt.jpa.domain.Orders;
import com.dhenton9000.birt.jpa.domain.SalesReport;
import com.dhenton9000.birt.jpa.repositories.EmployeesRepository;
import com.dhenton9000.birt.jpa.service.EmployeesService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dhenton
 */
public class EmployeesServiceImpl implements EmployeesService {
    
    private static final Logger LOG = LoggerFactory.getLogger(EmployeesServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EmployeesRepository employeesRepository;

    @Override
    public List<Employees> getAllEmployees() {
        Iterable<Employees> items = employeesRepository.findAll();
        return EntityUtils.makeCollection(items);
    }

    @Override
    public List<Orders> getOrdersForEmployee(Integer employeeId) {
        String qString = "select o "
                + " from Orders o "
                + " join  o.customer   cust "
                + " join  cust.employee   e "
                + " where e.employeeNumber = :id ";

        Query q = entityManager.createQuery(qString);
        q.setParameter("id", employeeId);
        List orders = q.getResultList();
        return orders;
    }

    @Override
    public SalesReport getSalesData(Integer employeeId) {
        Query q = computeSalesDataQuery(employeeId);
        q.setParameter("id", employeeId);
        SalesReport salesData = (SalesReport) q.getSingleResult();
        //   http://www.objectdb.com/java/jpa/query/jpql/select
        return salesData;
    }

    private Query computeSalesDataQuery(Integer employeeId) {
        String qString = "select  new com.dhenton9000.birt."
                + "jpa.domain.SalesReport(e.firstName, e.lastName,"
                + "SUM(details.priceEach),e.employeeNumber)"
                + " from  Orders o"
                + " join  o.orderDetails details"
                + " join  o.customer   cust "
                + " join  cust.employee   e ";
        if (employeeId != null) {
            qString = qString + " where e.employeeNumber = :id ";
        }
        qString = qString + " GROUP BY e.employeeNumber"
                + " ORDER BY e.lastName, e.firstName";
        Query q = entityManager.createQuery(qString);
        return q;
    }

    @Override
    public List<SalesReport> getSalesData() {
        Query q = computeSalesDataQuery(null);
        List<SalesReport> salesData = q.getResultList();

        //   http://www.objectdb.com/java/jpa/query/jpql/select
        return salesData;
    }

    @Override
    public List<Employees> getEmployeesForOffice(String officeCode) {
        String qString = "select employees from "
                + "Offices o join o.employees employees "
                + "where o.officeCode = :id ";

        Query q = entityManager.createQuery(qString);
        q.setParameter("id", officeCode);

        List<Employees> officeEmployees = q.getResultList();

        //   http://www.objectdb.com/java/jpa/query/jpql/select
        return officeEmployees;
    }

    @Override
    public Employees getById(Integer employeeId) {
        if (employeeId < 0) {
            throw new RuntimeException("negative employees not allowed");
        }
        return employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "The requested employeeId [" + employeeId
                + "] does not exist."));
    }

}
