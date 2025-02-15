package com.onz.bars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OnzDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnzDataApplication.class, args);
	}

}
