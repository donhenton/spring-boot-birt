/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.repositories;

import com.dhenton9000.birt.jpa.domain.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dhenton
 */
@Repository
public interface OrdersRepository extends CrudRepository<Orders,Integer>{
    
    
      
    @Query("SELECT e.orders from Customers e WHERE e.customerNumber = :customerNumber")
    List<Orders> findAllOrdersForCustomer(@Param("customerNumber") Integer customerNumber);
}