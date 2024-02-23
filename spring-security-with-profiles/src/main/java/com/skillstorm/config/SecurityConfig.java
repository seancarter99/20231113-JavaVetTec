package com.skillstorm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration // a class that defines beans
public class SecurityConfig {
	
	@Bean // Annotation indicating spring to create a bean from the return
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.httpBasic(Customizer.withDefaults());
		
//		http.csrf(csrf -> {
//			csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		});
		http.csrf(csrf -> csrf.disable()); // Not encouraged
		
		http.authorizeHttpRequests(requests -> {
			// GET requests to /movies or /movies/something, allow all. All else, authenticted
			requests.requestMatchers(HttpMethod.POST ,"/register").permitAll();
			requests.requestMatchers("/movies/**", "/movies").hasAnyRole("ADMIN");
			requests.anyRequest().authenticated();
		});
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	// Spring Expression Language
//	@Value("${frontend.redirect}") // Pulls from the env variables
//	private String redirectUrl;
//
//	Logger log = LoggerFactory.getLogger(SecurityConfig.class);
//	
//	// We can create our own beans depending on the profile that is active
//	@Bean
//	@Profile("dev") // Only create this bean in the dev environment
//	public String helloWorld() {
//		log.info(redirectUrl);
//		return "Hello World";
//	}
}
