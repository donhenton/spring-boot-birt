/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.service.impl.security;

import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.Users;
import com.dhenton9000.birt.jpa.repositories.security.UsersRepository;
import com.dhenton9000.birt.jpa.service.security.UsersService;
import com.dhenton9000.jpa.util.EntityUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    
    @PersistenceContext()
    private EntityManager entityManager;
    @Autowired
    private UsersRepository usersRepository;
     
  @Override
    public List<Users> findAll() {
        Iterable<Users> items = usersRepository.findAll();
        return EntityUtils.makeCollection(items);
    }

    @Override
    public Users findOne(Integer id) {
        return usersRepository.findOne(id);
    }

    @Override
    public Users findByLogin(String name) {
        return  usersRepository.findByLogin(name);
         
    }

    @Override
    public List<Applications> findAuthorizedApplications(Integer id) {
         
         
         String qString = "select applications "
                + " from Users users "
                + " join users.groupsSet groups "
                + " join groups.applicationsSet applications " 
                + " where users.userid = :id ";
                 
        Query q = this.entityManager.createQuery(qString);
        q.setParameter("id", id);
        return   q.getResultList();
    }

    @Override
    public List<Groups> findGroupsForUser(Integer id) {
         
         
         String qString = "select groups "
                + " from Users users "
                + " join users.groupsSet groups "
                + " where users.userid = :id ";
                 
        Query q = this.entityManager.createQuery(qString);
        q.setParameter("id", id);
        return   q.getResultList();
    }

    @Override
    public Users save(Users user) {
         return usersRepository.save(user);
    }

    @Override
    public void delete(Users user) {
        usersRepository.delete(user);
        
    }
    
    
    
}
