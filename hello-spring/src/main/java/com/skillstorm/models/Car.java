package com.skillstorm.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Car {
	
	/**
	 * Java Validations JSR-303
	 * 
	 * Bean Validation through Annotations
	 * 
	 * If not specified, Spring Web will handle with a 400 by default
	 * 
	 * @NotNull
	 * @NotBlank - For Strings, not null AND at least one non-whitespace char
	 * @NotEmpty - For Data Structures, must have items in it
	 * @Min - For numbers, min number required
	 * @Max - See above but opposite
	 * @Pattern - Takes in regex and is useful strings (think passwords)
	 * @Size - Takes a min and a max as optionals, if specified, those are 
	 *         the boundary
	 * @Email - Ensures it's an email
	 * @Past - Ensures a Date is in the past
	 * @Future - Ensures a Date is in the future
	 */
	
	/**
	 * {
	 *   "make": "",
	 *   "model": "",
	 *   "yr": 0,
	 *   "owner": 15
	 * }
	 */

	@NotBlank
	private String make;

	@NotBlank
	private String model;
	
	@NotNull
	@Min(1900)
	private Integer year;

	public Car() {
		super();
	}

	public Car(String make, String model, Integer year) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", year=" + year + "]";
	}
}
