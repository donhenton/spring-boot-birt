 
package com.dhenton9000.birt.jpa.repositories;


 
import com.dhenton9000.birt.jpa.domain.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomersRepository extends CrudRepository<Customers,Integer>{
    
    
    
   
}