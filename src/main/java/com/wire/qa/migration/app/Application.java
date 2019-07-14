package com.wire.qa.migration.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wire.qa.migration")
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

}
