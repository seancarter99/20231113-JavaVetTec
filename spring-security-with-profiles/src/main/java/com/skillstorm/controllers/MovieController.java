package com.skillstorm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	@GetMapping("/movies")
	public String movies() {
		return "Movies";
	}
	
	@GetMapping("/movies/{id}")
	public String movieId() {
		return "Specific movie";
	}
	
	@PostMapping("/movies")
	public String createMovie() {
		return "Movie created";
	}
	
}
