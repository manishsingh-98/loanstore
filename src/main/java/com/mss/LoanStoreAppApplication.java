package com.mss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LoanStoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanStoreAppApplication.class, args);
	}

}
