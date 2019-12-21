/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.configs;

import com.dhenton9000.birt.jpa.service.EmployeesService;
import com.dhenton9000.birt.jpa.service.OrdersService;
import com.dhenton9000.birt.jpa.service.ProductsService;
import com.dhenton9000.birt.jpa.service.impl.EmployeesServiceImpl;
import com.dhenton9000.birt.jpa.service.impl.OrdersServiceImpl;
import com.dhenton9000.birt.jpa.service.impl.ProductsServiceImpl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
 

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//https://github.com/netgloo/spring-boot-samples/tree/master/spring-boot-mysql-jpa-hibernate
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private Logger log = LoggerFactory.getLogger(DatabaseConfig.class);
    
    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;
 
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Bean
    public DataSource dataSource() {
        URI dbUrl = null;
        String dbString = env.getProperty("DATABASE_URL");
        log.debug("database string "+dbString);
        try {
             
           dbUrl = new URI(dbString);
            
        } catch (URISyntaxException ex) {
              
             throw new RuntimeException(ex.getMessage());
        }
        
       
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        String url = "jdbc:postgresql://" + dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath();
        log.debug("url "+url);
        dataSource.setUrl(url);
        log.debug("info user "+dbUrl.getUserInfo());
        dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
        dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);

        // Classpath scanning of jpa annotated classes
        entityManagerFactory.setPackagesToScan(
                env.getProperty("entitymanager.packagesToScan"));

        // Vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put(
                "hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        additionalProperties.put(
                "hibernate.show_sql",
                env.getProperty("hibernate.show_sql"));
        //additionalProperties.put(
        //    "hibernate.hbm2ddl.auto", 
        //    env.getProperty("hibernate.hbm2ddl.auto"));
        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }

    /**
     * Declare the transaction manager.
     *
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory.getObject());
        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then rethrown as one Spring's
     * unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     *
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public EmployeesService getEmployeeServiceBean() {
        return new EmployeesServiceImpl();
    }

    @Bean
    public OrdersService getOrdersServiceBean() {
        return new OrdersServiceImpl();
    }

    @Bean
    public ProductsService getProductsServiceBean() {
        return new ProductsServiceImpl();
    }
    
     
}
