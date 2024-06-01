package com.sandu.cashcompass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class CashCompassApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashCompassApplication.class, args);
	}

}
