package com.skillstorm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.models.AppUser;
import com.skillstorm.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {	
	
	// Spring needs to find a user by a username and check the password to see if match
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findById(username).orElseThrow(() -> {
			throw new UsernameNotFoundException("No user with " + username + " found");
		});
		return user;
	}
	
	/**
	 * If any runtime exception occurs in the method, will auto rollback
	 * If it returns normally, it will commit the transaction
	 */
	@Transactional // you're done
	public AppUser register(String username, String password) {
		/**
		 * 1. Ensure the username is not already taken
		 * 1b. If taken, throw some excetpion
		 * 2. Hash the user's password
		 * 3. Persist User to DB
		 */
		Optional<AppUser> optUser = userRepository.findById(username);
		
		if (optUser.isPresent()) {
			// Throw new exception for our controller to deal with
			// Or RestControllerAdvice
			throw new IllegalArgumentException("Username already taken");
		}
		// Username is not taken
		password = passwordEncoder.encode(password);
		AppUser user = new AppUser(username, password, "ROLE_USER");
		return userRepository.save(user);
	}
}
