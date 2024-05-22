package dev.grace.runnerzs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunnerzsApplication {

	private static final Logger logger = LoggerFactory.getLogger(RunnerzsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RunnerzsApplication.class, args);
		logger.info("Application started succeessfully");
	}

}
