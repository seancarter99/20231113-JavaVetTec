package com.skillstorm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.models.Address;

// @Repository // Putting this on an interface does NOTHING
public interface AddressRepository extends JpaRepository<Address, Long> {

}
