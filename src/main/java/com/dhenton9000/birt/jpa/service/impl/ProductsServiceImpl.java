/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl;

import com.dhenton9000.birt.jpa.domain.Products;
import com.dhenton9000.birt.jpa.repositories.ProductsRepository;
import com.dhenton9000.birt.jpa.service.ProductsService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dhenton
 */
public class ProductsServiceImpl implements ProductsService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductsRepository productsRepository;
    
    
    @Override
    public List<Products> getAllProducts() {
        
        Iterable<Products> items = productsRepository.findAll();
        return EntityUtils.makeCollection(items);
    }
    
}
