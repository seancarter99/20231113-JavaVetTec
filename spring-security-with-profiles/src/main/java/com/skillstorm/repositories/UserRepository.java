package com.skillstorm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.skillstorm.models.AppUser;

public interface UserRepository extends CrudRepository<AppUser, String>{

}
