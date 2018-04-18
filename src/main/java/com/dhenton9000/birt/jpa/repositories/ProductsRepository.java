/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.repositories;

import com.dhenton9000.birt.jpa.domain.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dhenton
 */
@Repository
public interface ProductsRepository extends CrudRepository<Products,String>{
    
}
