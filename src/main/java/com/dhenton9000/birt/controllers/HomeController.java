/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dhenton
 */

@Controller
public class HomeController {
    
    
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
     @RequestMapping("/data")
    public String data(Model model) {
        model.addAttribute("appTitle", "Data Models");
        return "pages/data";
    }
}
