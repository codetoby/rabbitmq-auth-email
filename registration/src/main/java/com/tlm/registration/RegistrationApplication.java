package com.tlm.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.tlm.registration", "com.tlm.core"})
@EntityScan(basePackages = {"com.tlm.core.user", "com.tlm.registration.verification"})
@EnableJpaRepositories(basePackages = { "com.tlm.core.user", "com.tlm.registration.verification" })
public class RegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

}
