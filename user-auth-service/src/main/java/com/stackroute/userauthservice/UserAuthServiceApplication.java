package com.stackroute.userauthservice;

import com.stackroute.userauthservice.repository.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
public class UserAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthServiceApplication.class, args);
	}

}
