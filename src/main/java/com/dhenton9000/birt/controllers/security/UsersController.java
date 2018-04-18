/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.controllers.security;

import com.dhenton9000.birt.controllers.support.ResourceAlreadyExistsException;
import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.Users;
import com.dhenton9000.birt.jpa.service.security.UsersService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = "application/json")
    @ApiOperation(value = "Find All Users", notes = "lists all Users")
    public List<Users> findAll() {
        return usersService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/authorized/apps/{id}", produces = "application/json")
    @ApiOperation(value = "Find Apps", notes = "lists all applications for which the user is authorized")
    public List<Applications> findAuthorizedApps(@PathVariable("id") Integer id) {
        return usersService.findAuthorizedApplications(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/authorized/groups/{id}", produces = "application/json")
    @ApiOperation(value = "Find Groups", notes = "lists all groups for which the user is a member")
    public List<Groups> findGroupsForUser(@PathVariable("id") Integer id) {
        return usersService.findGroupsForUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findOne/{id}", produces = "application/json")
    @ApiOperation(value = "Find a User", notes = "Find a User by id")
    public Users findOne(@PathVariable("id") Integer id) {
        return usersService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findByLogin/{login}", produces = "application/json")
    @ApiOperation(value = "Find a User", notes = "Find a User by login string")
    public Users findByLogin(@PathVariable("login") String login) {
        return usersService.findByLogin(login);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/save", produces = "application/json")
    @ApiOperation(value = "Save A User", notes = "Save changes to  a  User. It uses the id to change the login and username")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Users save(@RequestBody Users user) {

        Integer id = user.getUserid();
        Users foundUser = usersService.findOne(id);
        if (foundUser == null) {
            throw new ResourceNotFoundException("cannot find user [" + id + "]");
        }

        return usersService.save(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add", produces = "application/json")
    @ApiOperation(value = "Add A User", notes = "Add a user")
    public Users add(@RequestBody Users user) {

        Integer id = user.getUserid();
        Users foundUser = usersService.findOne(id);
        if (foundUser != null) {
            throw new ResourceAlreadyExistsException("user [" + id + "] exists already");
        }

        return usersService.save(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/checktrans", produces = "application/json")
    @ApiOperation(value = "Add A User", notes = "throws error if name is bozo after save")
    @Transactional
    public Users checkTrans(@RequestBody Users user) {

        Users newUser = usersService.save(user);
        if (user.getUsername() != null && user.getUsername().toUpperCase().contains("BOZO")) {
            throw new RuntimeException("cannot be a bozo!!");
        }

        return newUser;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete", produces = "application/json")
    @ApiOperation(value = "delete a user", notes = "Delete a user")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody Users user) {
        usersService.delete(user);
    }

}
