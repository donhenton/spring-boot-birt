/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt;

import com.dhenton9000.birt.jpa.service.impl.security.ApplicationsServiceImpl;
import com.dhenton9000.birt.jpa.service.impl.security.GroupsServiceImpl;
import com.dhenton9000.birt.jpa.service.impl.security.UsersServiceImpl;
import com.dhenton9000.birt.jpa.service.security.ApplicationsService;
import com.dhenton9000.birt.jpa.service.security.GroupsService;
import com.dhenton9000.birt.jpa.service.security.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author dhenton
 */
@Configuration
public class SecurityConfig {

    @Bean
    @Primary
    public UsersService getUsersServiceBean() {
        return new UsersServiceImpl();
    }

    @Bean
    @Primary
    public GroupsService getGroupsServiceBean() {
        return new GroupsServiceImpl();
    }

    @Bean
    @Primary
    public ApplicationsService getApplicationsServiceBean() {
        return new ApplicationsServiceImpl();
    }

}
