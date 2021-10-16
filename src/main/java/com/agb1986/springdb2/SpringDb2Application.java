package com.agb1986.springdb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringDb2Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringDb2Application.class, args);
	}
}
