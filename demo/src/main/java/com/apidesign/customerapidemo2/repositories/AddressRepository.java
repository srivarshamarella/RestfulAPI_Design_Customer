package com.apidesign.customerapidemo2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apidesign.customerapidemo2.models.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

}