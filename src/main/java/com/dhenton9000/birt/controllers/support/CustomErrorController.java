package com.dhenton9000.birt.controllers.support;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";
 
    private final ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping("/404")
    public String pageNotFound(Model model, WebRequest webRequest) {
        model.addAttribute("error", getErrorAttributes(webRequest, true));
        return "404";
    }


    private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

}
