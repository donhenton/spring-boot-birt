/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl.security;

import com.dhenton9000.birt.controllers.support.ResourceNotFoundException;
import com.dhenton9000.birt.controllers.support.ResourceAlreadyExistsException;
import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.Users;
import com.dhenton9000.birt.jpa.repositories.security.ApplicationsRepository;
import com.dhenton9000.birt.jpa.repositories.security.GroupsRepository;
import com.dhenton9000.birt.jpa.repositories.security.UsersRepository;
import com.dhenton9000.birt.jpa.service.security.GroupsService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupsServiceImpl implements GroupsService {

    @PersistenceContext()
    private EntityManager entityManager;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Override
    public List<Groups> findAll() {
        Iterable<Groups> items = groupsRepository.findAll();
        return EntityUtils.makeCollection(items);
    }

    @Override
    public Groups findOne(Integer id) {
        return this.check404(groupsRepository.findOne(id));
    }

    @Override
    public List<Groups> findByName(String name) {
        Iterable<Groups> items = groupsRepository.findByName(name);
        return EntityUtils.makeCollection(items);
    }

    @Override
    public List<Applications> findApplicationsForGroup(Integer id) {

        Groups g = groupsRepository.findOne(id);
        if (g == null) {
            throw new ResourceNotFoundException("could not find group " + id);
        }
        return new ArrayList<Applications>(g.getApplicationsSet());
    }

    @Override
    public List<Users> findUsersForGroup(Integer id) {

        Groups g = groupsRepository.findOne(id);
        if (g == null) {
            throw new ResourceNotFoundException("could not find group " + id);
        }
        return new ArrayList<Users>(g.getUsersSet());
    }

    @Override
    public List<Groups> findGroupsContainsApplication(Integer appId) {
        String qString = "select groups "
                + " from Groups groups "
                + " join groups.applicationsSet applications "
                + " where applications.id = :id ";

        Query q = this.entityManager.createQuery(qString);
        q.setParameter("id", appId);
        return q.getResultList();
    }

    @Override
    public List<Applications> findApplicationsNotInGroup(Integer groupId) {

        String innerQuery = " SELECT apps.id from Groups g "
                + " join g.applicationsSet apps "
                + " where g.id = :id ";
 

        String qString = "select applications "
                + " from Applications applications "
                + " where applications.id NOT IN ( "
                + innerQuery 
                + " )";

        Query q = this.entityManager.createQuery(qString);
        q.setParameter("id", groupId);
        return q.getResultList();

    }
    
    @Override
    public List<Users> findUsersNotInGroup(Integer groupId) {

        String innerQuery = " SELECT users.userid from Groups g "
                + " join g.usersSet users "
                + " where g.id = :id ";
 

        String qString = "select users "
                + " from Users users "
                + " where users.userid NOT IN ( "
                + innerQuery 
                + " )";

        Query q = this.entityManager.createQuery(qString);
        q.setParameter("id", groupId);
        return q.getResultList();

    }

    @Override
    @Transactional
    public Groups save(Groups g) {
        return groupsRepository.save(g);
    }

    @Override
    @Transactional
    public void delete(Groups g) {
        
        groupsRepository.delete(g);
    }

    @Override
    @Transactional
    public Users addUserToGroup(Integer userId, Integer groupId) {

        Groups g = this.findOne(groupId);
        if (g == null) {
            throw new ResourceNotFoundException("cannot find group " + groupId);
        }

        Users u = usersRepository.findOne(userId);
        if (u == null) {
            throw new ResourceNotFoundException("cannot find user " + userId);
        }

        if (g.getUsersSet().contains(u)) {
            throw new ResourceAlreadyExistsException("user already in group");
        }

        Set<Users> users = g.getUsersSet();
        users.add(u);

        g.setUsersSet(users);
        return u;
    

    }

    @Override
    @Transactional
    public Users removeUserFromGroup(Integer userId, Integer groupId) {

        Groups g = this.findOne(groupId);
        if (g == null) {
            throw new ResourceNotFoundException("cannot find group " + groupId);
        }

        Users u = usersRepository.findOne(userId);
        if (u == null) {
            throw new ResourceNotFoundException("cannot find user " + userId);
        }

        if (!g.getUsersSet().contains(u)) {
            throw new ResourceNotFoundException(String.format("user %d should be in group %d but isn't", userId, groupId));
        }

        Set<Users> users = g.getUsersSet();
//        HashSet<Users> cutList = users.stream().filter(ut -> {
//
//            return !ut.getUserid().equals(u.getUserid());
//        }).collect(Collectors.toCollection(HashSet::new));
        users.remove(u);
        g.setUsersSet(users);
        return u;
       

    }

    @Override
    @Transactional
    public Applications removeApplicationFromGroup(Integer appId, Integer groupId) {
        Groups g = this.findOne(groupId);
        if (g == null) {
            throw new ResourceNotFoundException("cannot find group " + groupId);
        }

        Applications a = applicationsRepository.findOne(appId);
        if (a == null) {
            throw new ResourceNotFoundException("cannot find app " + appId);
        }

        if (!g.getApplicationsSet().contains(a)) {
            throw new ResourceNotFoundException(String.format("app %d should be in group %d but isn't", appId, groupId));
        }

        Set<Applications> apps = g.getApplicationsSet();

        apps.remove(a);
        g.setApplicationsSet(apps);
        return a;
    }

    @Override
    @Transactional
    public Applications addApplicationToGroup(Integer appId, Integer groupId) {
        Groups g = this.findOne(groupId);
        if (g == null) {
            throw new ResourceNotFoundException("cannot find group " + groupId);
        }

        Applications a = applicationsRepository.findOne(appId);
        if (a == null) {
            throw new ResourceNotFoundException("cannot find app " + appId);
        }

        if (g.getApplicationsSet().contains(a)) {
            throw new ResourceAlreadyExistsException("app already in group");
        }

        Set<Applications> apps = g.getApplicationsSet();
        apps.add(a);
        g.setApplicationsSet(apps);
        return a;

    }

    private Groups check404(Groups findOne) {
        if (findOne == null) {
            throw new ResourceNotFoundException("nothing found");
        } else {
            return findOne;
        }
    }

}
