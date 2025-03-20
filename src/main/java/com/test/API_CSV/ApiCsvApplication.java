package com.test.API_CSV;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiCsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCsvApplication.class, args);
	}

}
