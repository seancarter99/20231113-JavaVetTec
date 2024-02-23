package com.skillstorm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.models.Customer;
import com.skillstorm.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	// /customers?min=21&max=30
	@GetMapping
	public List<Customer> findAll(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
		if (min == null || max == null) {
			// find all customers
			return null;
		}
		// if BOTH min AND max are present
		
		return customerService.findCustomersWithinAgeRange(min, max);
	}
}
