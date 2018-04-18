/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.config;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import org.springframework.test.context.junit4.SpringRunner;
/**
 *
 * @author dhenton
 */


@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class ConfigTests {
    private Logger log = LoggerFactory.getLogger(ConfigTests.class);
    
    
    @Autowired
    private Environment env;
    
    @Value("#{environment['DATABASE_URL']}")
    private String databaseURL;
    
    @Value("${hibernate.dialect}")
    private String dialect;
    
    @Test
    public void testEnvConfig()
    {
        assertTrue(true);
        assertNotNull(env);
        assertNotNull(databaseURL);
        assertEquals(dialect,"org.hibernate.dialect.PostgreSQLDialect"); // set in application.properties
        assertEquals(databaseURL,"postgres://test:test@localhost:5433/jdatabase");  //set in surefire plugin
    }
    
    
}
