package com.skillstorm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.models.Customer;
import com.skillstorm.repositories.CustomerRepository;

/**
 * ComponentScan - Looks at all classes from the public void main method package
 *  on down
 *  
 *  It looks for any class annotated with @Component and will automatically
 *  create a "bean" of it
 *  
 *  // Stereotype annotations
 * 
 */

 // Semantic form @Component
@Service
public class CustomerService {
	
	// If i autowire anything in this class, when creating this bean
	// spring will "inject" that dependency in it (through constructor, setter, or reflection)

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findCustomersWithinAgeRange(int minAge, int maxAge) {
		if (minAge > maxAge) {
			throw new IllegalArgumentException("Min Age cannot be more than Max Age");
		}
		
		return customerRepository.iCanNameThisMethodWhatever(minAge, maxAge);
//		return customerRepository.findByAgeBetween(minAge, maxAge);
	}
}
