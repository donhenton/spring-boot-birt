/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers.security;

import com.dhenton9000.birt.controllers.support.ResourceAlreadyExistsException;
import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.Users;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.dto.GroupDTO;
import com.dhenton9000.birt.jpa.service.security.GroupsService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec/groups")
public class GroupsController  {

    @Autowired
    private GroupsService groupsService;

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Find All Groups", notes = "lists all Groups")
   
    public List<Groups> findAll() {
        return groupsService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/applications/{id}", produces = "application/json")
    @ApiOperation(value = "Find All Applications", notes = "lists all Applications for a group")
   
    public List<Applications> findApplicationsForGroup(@PathVariable("id") Integer id) {
        return groupsService.findApplicationsForGroup(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/applications/not/{id}", produces = "application/json")
    @ApiOperation(value = "Find All Applications Not in Group", notes = "lists all Applications not contained in that group")
   
    public List<Applications> findApplicationsNotInGroup(@PathVariable("id") Integer id) {

        Groups foundGroup = groupsService.findOne(id);
        if (foundGroup == null) {
            return new ArrayList<Applications>();
        }

        return groupsService.findApplicationsNotInGroup(id);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, path = "/users/not/{id}", produces = "application/json")
    @ApiOperation(value = "Find All Users Not in Group", notes = "lists all Users not contained in that group")
 
    public List<Users> findUsersNotInGroup(@PathVariable("id") Integer id) {

        Groups foundGroup = groupsService.findOne(id);
        if (foundGroup == null) {
            return new ArrayList<Users>();
        }

        return groupsService.findUsersNotInGroup(id);
    }
    

    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}", produces = "application/json")
    @ApiOperation(value = "Find All Users", notes = "lists all Users for a group")
 
    public List<Users> findUsersForGroup(@PathVariable("id") Integer id) {
        Groups foundGroup = groupsService.findOne(id);
        if (foundGroup == null) {
            throw new ResourceNotFoundException("cannot find group [" + id + "]");
        }
        return groupsService.findUsersForGroup(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findOne/{id}", produces = "application/json")
    @ApiOperation(value = "Find A Group", notes = "find Group")
 
    public Groups findOne(@PathVariable("id") Integer id) {
        return groupsService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findByName/{name}", produces = "application/json")
    @ApiOperation(value = "Find A Group by name", notes = "find a group by group name")
 
    public List<Groups> findByName(@PathVariable("name") String name) {
        return groupsService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findGroup/containing/application/{appId}", produces = "application/json")
    @ApiOperation(value = "Find A Group by application", notes = "find a group that contains the specified application")
 
    public List<Groups> findGroupsContainsApplication(@PathVariable("appId") Integer appId) {
        return groupsService.findGroupsContainsApplication(appId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/save", produces = "application/json")
    @ApiOperation(value = "Save A Group", notes = "Save changes to  a  Group")
    @ResponseStatus(HttpStatus.OK)
 
    public Groups save(@RequestBody Groups group) {

        Integer id = group.getId();
        Groups foundGroup = groupsService.findOne(id);
        if (foundGroup == null) {
            throw new ResourceNotFoundException("cannot find group [" + id + "]");
        }

        return groupsService.save(group);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add", produces = "application/json")
    @ApiOperation(value = "Add A Group", notes = "Add a Group")
    public Groups add(@RequestBody Groups group) {

        Integer id = group.getId();
        Groups foundGroup = null;

        try {
            foundGroup = groupsService.findOne(id);
        } catch (Exception e) {
        }

        if (foundGroup != null) {
            throw new ResourceAlreadyExistsException("group [" + id + "] exists already");
        }

        return groupsService.save(group);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    @ApiOperation(value = "delete a group", notes = "Delete a Group")
    @ResponseStatus(HttpStatus.OK)
 
    public void delete(@RequestBody Groups group) {
        Integer id = group.getId();
        Groups foundGroup = groupsService.findOne(id);
        if (foundGroup == null) {
            throw new ResourceNotFoundException("cannot find group [" + id + "]");
        }
        groupsService.delete(group);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/removeUser/{userId}/group/{groupId}", produces = "application/json")
    @ApiOperation(value = "remove a user from a group", notes = "remove a user from a group return the user removed")
 
    public Users removeUserFromGroup(@PathVariable("userId") Integer userId, @PathVariable("groupId") Integer groupId) {

        Groups foundGroup = groupsService.findOne(groupId);
        if (foundGroup == null) {
            throw new ResourceNotFoundException("cannot find group [" + groupId + "]");
        }
        return groupsService.removeUserFromGroup(userId, groupId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/addUser/{userId}/group/{groupId}", produces = "application/json")
    @ApiOperation(value = "add a user to a group", notes = "add a user to a group return the added user")

 
    public Users addUserToGroup(@PathVariable("userId") Integer userId, @PathVariable("groupId") Integer groupId) {
        Groups foundGroup = groupsService.findOne(groupId);
        if (foundGroup == null) {
            throw new ResourceNotFoundException("cannot find group [" + groupId + "]");
        }
        return groupsService.addUserToGroup(userId, groupId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/removeApplication/{appId}/group/{groupId}", produces = "application/json")
    @ApiOperation(value = "remove an application from a group", notes = "remove an application from a group return the application removed")
 
    public Applications removeApplicationFromGroup(@PathVariable("appId") Integer appId, @PathVariable("groupId") Integer groupId) {
        Groups foundGroup = groupsService.findOne(groupId);
        if (foundGroup == null) {
            throw new ResourceNotFoundException("cannot find group [" + groupId + "]");
        }
        return groupsService.removeApplicationFromGroup(appId, groupId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/addApplication/{appId}/group/{groupId}", produces = "application/json")
    @ApiOperation(value = "add an application to a group", notes = "add an application to a group return the added application")
 
    public Applications addApplicationToGroup(@PathVariable("appId") Integer appId, @PathVariable("groupId") Integer groupId) {
        Groups foundGroup = groupsService.findOne(groupId);
        if (foundGroup == null) {
            throw new ResourceNotFoundException("cannot find group [" + groupId + "]");
        } 
        return groupsService.addApplicationToGroup(appId, groupId);
    }

    
}
