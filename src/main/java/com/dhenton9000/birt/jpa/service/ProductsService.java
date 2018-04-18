/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service;

 
import com.dhenton9000.birt.jpa.domain.Products;
import java.util.List;

/**
 *
 * @author dhenton
 */
public interface ProductsService {
    
    public List<Products> getAllProducts();
}
