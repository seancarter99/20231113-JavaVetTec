package com.skillstorm.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * TWO TYPES OF CONTROLLERS
 * 
 * @Controller - Return web pages or "Views"
 * @RestController - Returns JSON data
 * 
 * Jackson Object Relational Mapper - Maps JSON -> Java Objects and visa versa
 */

@RestController
public class MovieController {
	
	// Will be removed when database is added
	private ArrayList<String> movies = new ArrayList<String>(Arrays.asList(
			"Bladerunner", "Bladerunner 2047", "Fifth Element"
			));

	/**
	 * Find all movies
	 * 
	 * Find movie by id
	 * 
	 * Create a movie
	 * 
	 * Update a movie
	 * 
	 * Delete a movie
	 */
	
	// /movies
//	@RequestMapping(method = RequestMethod.GET, path = "/movies")
	@GetMapping("/movies")
	public List<String> findAllMovies() {
		// Default return will be 200 OK
		return movies;
	}
	
	// Returning different status codes
	
	// /movies/5
	@GetMapping("/movies/{id}")
	public ResponseEntity<String> findMovieById(@PathVariable int id) {
		// if id < 0 or > length of the array, send 204 status back
		if (id < 0 || id >= movies.size()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(movies.get(id)); // Return that movie with 200 ok
	}
	
	// CREATE
	// /movies
	// The data comes in the request body
	@PostMapping("/movies")
	public ResponseEntity<String> createMovie(@RequestBody String movie) {
		movies.add(movie);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// UPDATE
	// /movies/{id}
	@PutMapping("/movies/{id}")
	public ResponseEntity<String> updateMovie(@RequestBody String movie, @PathVariable int id) {
		if (id < 0 || id >= movies.size()) {
			return ResponseEntity.notFound().build(); // 404
		}
		
		movies.set(id, movie);
		return ResponseEntity.ok(movie); // 200 OK is fine for PUT
	}
	
	// DELETE
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable int id) {
		if (id < 0 || id >= movies.size()) {
			return ResponseEntity.notFound().build(); // 404
		}
		
		String movie = movies.remove(id);
		return ResponseEntity.ok(movie);
	}
}
