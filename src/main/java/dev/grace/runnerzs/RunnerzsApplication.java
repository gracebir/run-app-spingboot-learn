package dev.grace.runnerzs;

import dev.grace.runnerzs.run.Location;
import dev.grace.runnerzs.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.Chronology;

@SpringBootApplication
public class RunnerzsApplication {

	private static final Logger logger = LoggerFactory.getLogger(RunnerzsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RunnerzsApplication.class, args);
		logger.info("Application started succeessfully ===========>-----");
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1, "First run", LocalDateTime.now(), LocalDateTime.now(), 5, Location.OUTDOOR);
			logger.info("Run " + run);
		};
	}

}
