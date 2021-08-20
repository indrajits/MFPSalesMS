/**
 * 
 */
package com.mazdausa.mfpsalesms.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Indrajit Sen
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket mfpSalesApi() {
		return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.basePackage("com.mazdausa.mfpsalesms.controller"))            
		          .paths(PathSelectors.any())                          
		          .build()
		          .apiInfo(metaData());
	}

	@Bean
	public ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				"MFP Sales Microservice",
				"Spring Boot Microservice for MFP Sales Details",
				"1.0",
				"Terms of service",
				new Contact("Interra Information Technologies", "https://www.interrait.com/", "business@interrait.com"),
				"License Details",
				"Mazda North American Operations", Collections.emptyList());
		return apiInfo;
	}

}
