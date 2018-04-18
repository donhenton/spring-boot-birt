/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers;

import com.dhenton9000.birt.jpa.domain.Products;
import com.dhenton9000.birt.jpa.service.ProductsService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/birt/products")
public class ProductsController  {
     @Autowired
    private ProductsService productsService;

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Get All Products", notes = "lists all Products at Classic Cars")
    public List<Products> getAllProducts() {
         return productsService.getAllProducts();
    }
}
