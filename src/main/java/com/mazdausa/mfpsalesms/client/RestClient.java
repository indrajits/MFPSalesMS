/**
 * 
 */
package com.mazdausa.mfpsalesms.client;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Indrajit Sen
 *
 */
@Component
public class RestClient {
	
	private RestTemplate restTemplate;
	
	public RestClient() {
		// TODO Auto-generated constructor stub
		restTemplate = new RestTemplate();
	}
	
	public RestTemplate getRestTemplate() {
		MappingJackson2HttpMessageConverter converter = 
				new MappingJackson2HttpMessageConverter(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		
		return restTemplate;
	}

}
