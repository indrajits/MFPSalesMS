package com.mazdausa.mfpsalesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class MfpSaleMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfpSaleMsApplication.class, args);
	}

}
