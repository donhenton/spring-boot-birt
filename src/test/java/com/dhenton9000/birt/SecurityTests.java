/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt;

import com.dhenton9000.birt.configs.DatabaseConfig;
import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.Users;
import com.dhenton9000.birt.jpa.service.security.ApplicationsService;
import com.dhenton9000.birt.jpa.service.security.GroupsService;
import com.dhenton9000.birt.jpa.service.security.UsersService;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dhenton
 */
@RunWith(SpringRunner.class)
@Rollback
@Transactional
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:test.properties")
@ContextConfiguration(classes = {SecurityConfig.class, DatabaseConfig.class})
public class SecurityTests {

    Logger log = LoggerFactory.getLogger(SecurityTests.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private GroupsService groupsService;
    @Autowired
    private ApplicationsService applicationsService;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testBean() {
        assertNotNull(usersService);
        assertNotNull(groupsService);
    }

    @Test
    public void checkUsers() {
        Users u = usersService.findOne(5);
        assertNotNull(u);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void checkUsersFail() {
        Users u = usersService.findOne(-11);

    }

    @Test
    public void testFindGroupForApplication() {
        Groups g = groupsService.findGroupsContainsApplication(26).get(0);
        assertEquals(g.getGroupName(), "PowerBuilder");
    }

    @Test
    public void testGrantUserAccess() {
        Users u = usersService.findByLogin("gaw");
        assertNotNull(u);

        Applications app = applicationsService.findOne(26);

        assertNotNull(app);
        Groups g = groupsService.findGroupsContainsApplication(26).get(0);
        assertNotNull(g);
        assertEquals("gaw", u.getLogin());
        List<Applications> apps = usersService.findAuthorizedApplications(u.getUserid());
        assertFalse(apps.contains(app));
        Set<Users> users = g.getUsersSet();
        users.add(u);
        g.setUsersSet(users);
        groupsService.save(g);
        entityManager.flush();
        entityManager.clear();
        apps = usersService.findAuthorizedApplications(u.getUserid());
        assertTrue(apps.contains(app));

    }

}
