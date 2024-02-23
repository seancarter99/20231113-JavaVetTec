package com.skillstorm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.skillstorm.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	// Custom Queries
	// Method Name
	// @Query
	
	// Find customers with an age between x and y
	
	List<Customer> findByAgeBetween(int x, int y);
	
	// Uses own query syntax called JPQL
//	@Query(nativeQuery = true, value = "")
//	@Query("SELECT * FROM Customers WHERE age BETWEEN ? AND ?")
	@Query("SELECT c FROM Customer c WHERE c.age BETWEEN ?1 AND ?2")
	List<Customer> iCanNameThisMethodWhatever(int x, int y);
}
