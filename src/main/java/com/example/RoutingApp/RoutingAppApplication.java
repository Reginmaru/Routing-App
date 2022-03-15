package com.example.RoutingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RoutingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutingAppApplication.class, args);
	}

}
