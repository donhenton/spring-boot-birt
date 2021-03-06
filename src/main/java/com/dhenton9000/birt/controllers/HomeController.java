/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dhenton
 */

@Controller
public class HomeController {
    
     @Value("${graphql.endpoint}")
    private String graphqlUrl;
    
    
     @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("appTitle", "Spring Boot");
        return "pages/home";
    }
    
     @RequestMapping("/demos")
    public String demos(Model model) {
        model.addAttribute("appTitle", "Application Demos");
        return "pages/demos";
    }
    
     @RequestMapping("/demoForGraphQL")
    public String graphql(Model model) {
        model.addAttribute("appTitle", "GraphQL Demos");
        model.addAttribute("graphqlUrl",graphqlUrl);
        return "pages/graphql";
    }
     @RequestMapping("/graphQLQueries")
    public String graphqlQueries(Model model) {
        model.addAttribute("appTitle", "GraphQL Queries");
        
        return "pages/graphqlQueries";
    }
    
     @RequestMapping("/data")
    public String data(Model model) {
        model.addAttribute("appTitle", "Data Models");
        return "pages/data";
    }
}
