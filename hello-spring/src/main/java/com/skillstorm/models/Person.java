package com.skillstorm.models;

import java.util.List;
import java.util.Objects;

public class Person {

	private Long id;
	private String name;
	private List<Car> cars;

	public Person(Long id, String name, List<Car> cars) {
		super();
		this.id = id;
		this.name = name;
		this.cars = cars;
	}

	public Person() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cars, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(cars, other.cars) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", cars=" + cars + "]";
	}
}
