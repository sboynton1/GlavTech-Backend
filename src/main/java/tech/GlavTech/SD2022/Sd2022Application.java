package tech.GlavTech.SD2022;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.pods.register.PasswordHandler;
import tech.GlavTech.SD2022.repo.UserRepo;
import tech.GlavTech.SD2022.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SpringBootApplication
public class Sd2022Application {

	private static UserService userService;
	private static UserRepo userRepo;
	private static PasswordHandler handler;

	public Sd2022Application(UserService userService, UserRepo userRepo, PasswordHandler handler) {
		this.userService = userService;
		this.userRepo = userRepo;
		this.handler = handler;
	}

	public static void main(String[] args) {
		SpringApplication.run(Sd2022Application.class, args);


		if(!userRepo.existsByUsername("groot")) {
            User Groot = new User();
			System.out.println();
			Groot.setFirstName("Groot");
			Groot.setLastName(" ");
			Groot.setAddress("Root");
			Groot.setCity("Carmel");
			Groot.setState("Indiana");
			Groot.setZip("12345");
			Groot.setPhone("1234567890");
			Groot.setUsername("groot");
			String pass = "";
			try {
				pass = handler.getSHA("Password1!");
			} catch (NoSuchAlgorithmException a) {
				System.out.println("ERROR DURING ENCRYPTION");
			}
			Groot.setPassword(pass);
			Groot.setAdmin(true);
			userService.addUser(Groot);
		}

	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
