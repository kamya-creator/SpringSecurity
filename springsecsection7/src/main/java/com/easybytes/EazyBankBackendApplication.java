package com.easybytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/*
as we have defined our entity and repository package under root folder we don't need
@EntityScan and @EnableJpaRepository annotations
 */
@EntityScan("com.easybytes.model")
@EnableJpaRepositories("com.easybytes.repository")
/*
This annotation is optional if you have added spring security dependency in your pom.xml
If you are in Spring framework project and want to implement security then, this annotation is mandatory
@EnableWebSecurity
*/

@SpringBootApplication
public class EazyBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApplication.class, args);
	}

}
