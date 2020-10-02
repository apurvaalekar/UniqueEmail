package com.fetch.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniqueEmailApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UniqueEmailApplication.class, args);
	}
	
	
}
