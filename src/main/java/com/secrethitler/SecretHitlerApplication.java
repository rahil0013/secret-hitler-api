package com.secrethitler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
SecretHitlerApplication.class,
Jsr310JpaConverters.class //registers jpa converter
})
public class SecretHitlerApplication {

	@PostConstruct
	void init() {
		//Java 8 Date/Time fields in the domain models automatically get converted to SQL types when we persist them in the database.
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SecretHitlerApplication.class, args);
	}
}