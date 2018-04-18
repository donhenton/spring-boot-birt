/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.security;

import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.Users;

import java.util.List;

/**
 *
 * @author dhenton
 */
public interface GroupsService {

    List<Groups> findAll();

    Groups findOne(Integer id);

    List<Groups> findByName(String name);

    List<Applications> findApplicationsForGroup(Integer id);
    
    List<Applications> findApplicationsNotInGroup(Integer id);
    
    List<Users> findUsersForGroup(Integer id);
    
    List<Users> findUsersNotInGroup(Integer id);

    List<Groups> findGroupsContainsApplication(Integer appId);

    Groups save(Groups g);

    void delete(Groups g);

    Users removeUserFromGroup(Integer userId, Integer groupId);

    Users addUserToGroup(Integer userId, Integer groupId);

    Applications removeApplicationFromGroup(Integer appId, Integer groupId);

    Applications addApplicationToGroup(Integer appId, Integer groupId);

}
