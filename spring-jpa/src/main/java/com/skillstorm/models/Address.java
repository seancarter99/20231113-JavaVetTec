package com.skillstorm.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Designates this class as an "entity" to be mapped to the db
@Table(name = "customer_address")
public class Address {

	// All properties of a class are seen as columns
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String street;
	
	@Column
	private String state;
	
	@Column
	private String city;
	
	/**
	 * FetchType Lazy will fetch the data ONLY WHEN YOU ACCESS IT getX()
	 * FetchType Eager (default) will fetch the data immediately
	 */
	
	// This can be ommitted if you're absolutely not going to use it
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY) // THIS IS NOT A COLUMN
	private List<Customer> customers; // JPA WILL JOIN WITH CUSTOMER TO GET THIS DATA

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
}
