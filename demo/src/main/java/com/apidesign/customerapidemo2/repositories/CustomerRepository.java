package com.apidesign.customerapidemo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apidesign.customerapidemo2.models.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
