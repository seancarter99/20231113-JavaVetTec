package com.skillstorm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.models.Address;
import com.skillstorm.repositories.AddressRepository;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping
	public Iterable<Address> findAllAddresses(
				@RequestParam(required = false) Integer page,
				@RequestParam(required = false) Integer size
			) {
		// SELECT * FROM ADDRESS;
		if (page == null || size == null) {
//			long count = addressRepository.count();
			return addressRepository.findAll();	
		}
		Page<Address> addressPage = addressRepository.findAll(PageRequest.of(page - 1, size));
		
		return addressPage;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Address> findAddressById(@PathVariable long id) {
		Optional<Address> addr = addressRepository.findById(id);
		if (addr.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(addr.get());
		}
		
		// If not empty, then return address
		// Else, throw an exception inside a lambda
//		Address address = addr.orElseThrow(() -> {
//			// The exception thrown in this lambda MUST BE OF RUNTIMEEXCEPTION
//			throw new IllegalArgumentException("No Address Found");
//		});
	}
	
	// /addresses
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		Address createdAddress = addressRepository.save(address);
		
		// createdAddress has an id, other one does not
		return new ResponseEntity<>(createdAddress, HttpStatusCode.valueOf(201));
	}
	
	// /addresses/{id}
	@PutMapping("/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable long id, @RequestBody Address address) {
		// Ensure the item is in the database
		Optional<Address> optAddress = addressRepository.findById(id);
		// If it's not, return a 404
		if (optAddress.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Address dbAddress = optAddress.get();
		
		address.setId(dbAddress.getId());
		
		// The JSON did not contain a city field
		if (address.getCity() == null) {
			// Use the one saved in the db
			// AKA, do not update this field
			address.setCity(dbAddress.getCity());
		}
		
		// if address passed in is null, use the dbAddress value instead
		// address.setValue(db.getValue());
		
		// If it is, update the database with the new values
		return ResponseEntity.ok(addressRepository.save(address));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		addressRepository.deleteById(id);
		return ResponseEntity.ok(null);
	}
}
