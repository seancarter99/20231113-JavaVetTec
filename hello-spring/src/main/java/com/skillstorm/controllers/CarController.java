package com.skillstorm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.models.Car;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars") // All mappings have /cars prefixed to it
public class CarController {

	private static final Logger log = LoggerFactory.getLogger(CarController.class);
	
	// Pass in the data to create a car
	
	// @Valid triggers the validations
//	@RequestMapping(method = RequestMethod.POST , path = "/cars")
	@PostMapping // /cars
	public Car createCar(@Valid @RequestBody Car car) {
//		System.out.println(car);
		log.warn(car.toString());
		return car;
	}
	
}
