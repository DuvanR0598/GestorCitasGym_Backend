package com.udea.energym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EnergymApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergymApplication.class, args);
	}

}
