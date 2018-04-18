/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt;

import com.dhenton9000.birt.configs.DatabaseConfig;
import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.Users;
import com.dhenton9000.birt.jpa.service.security.ApplicationsService;
import com.dhenton9000.birt.jpa.service.security.GroupsService;
import com.dhenton9000.birt.jpa.service.security.UsersService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Rollback
@Transactional
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:test.properties")
@ContextConfiguration(classes = {SecurityConfig.class, DatabaseConfig.class})
public class GroupServiceTests {

    @Autowired
    private UsersService usersService;
    @Autowired
    private GroupsService groupsService;
    @Autowired
    private ApplicationsService applicationsService;
    @PersistenceContext
    private EntityManager entityManager;

    @Test(expected = ResourceNotFoundException.class)
    public void testSaveToGroupWhenGroupUserDontExist() {
        Integer userId = -1;
        Integer groupId = -1;
        groupsService.addUserToGroup(userId, groupId);
    }

    @Test
    public void testSaveUserToGroup() {
        Users u = usersService.findByLogin("gaw");
        assertNotNull(u);
        Groups g = groupsService.findGroupsContainsApplication(26).get(0);
        Integer userId = u.getUserid();
        Integer groupId = g.getId();
        assertFalse(g.getUsersSet().contains(u));
        groupsService.addUserToGroup(userId, groupId);
        entityManager.flush();
        entityManager.clear();
        u = usersService.findByLogin("gaw");
        assertNotNull(u);
        g = groupsService.findGroupsContainsApplication(26).get(0);
        assertTrue(g.getUsersSet().contains(u));

        groupsService.removeUserFromGroup(userId, groupId);

        entityManager.flush();
        entityManager.clear();
        u = usersService.findByLogin("gaw");
        assertNotNull(u);
        g = groupsService.findGroupsContainsApplication(26).get(0);
        assertFalse(g.getUsersSet().contains(u));

    }
    
    @Test
    public void testAddGroup()
    {
        
        List<Groups> groupsList = groupsService.findAll();
        int firstCount = groupsList.size();
        
        Groups newGroup = new Groups();
        newGroup.setGroupName("bonzo group");
        newGroup = groupsService.save(newGroup);
        Integer newId = newGroup.getId();
        assertNotNull(newId);
        entityManager.flush();
        entityManager.clear();
        groupsList = groupsService.findAll();
        assertEquals(groupsList.size(),firstCount + 1);
        newGroup = groupsService.findOne(newId);
        assertNotNull(groupsList);
        assertNotNull(newGroup);
        assertTrue(groupsList.contains(newGroup));
         
        groupsService.delete(newGroup);
        entityManager.flush();
        entityManager.clear();
        newGroup = groupsService.findOne(newId);
        assertNull(newGroup);
        
    }
    
    

}
