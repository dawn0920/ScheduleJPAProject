package org.example.schedulejpaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleJpaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleJpaProjectApplication.class, args);
	}

}
