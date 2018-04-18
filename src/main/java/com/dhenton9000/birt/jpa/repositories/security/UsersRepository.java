/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.repositories.security;

 
import com.dhenton9000.birt.jpa.domain.security.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dhenton
 */
@Repository
public interface UsersRepository extends CrudRepository<Users,Integer>{
    
      @Query("SELECT u FROM Users u WHERE u.login = :login")
    Users findByLogin(@Param("login") String login);
}
