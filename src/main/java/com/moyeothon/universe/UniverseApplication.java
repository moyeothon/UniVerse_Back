package com.moyeothon.universe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class UniverseApplication {

	public static void main(String[] args) {
		log.info("UniverseApplication Start");
		SpringApplication.run(UniverseApplication.class, args);
	}

}
