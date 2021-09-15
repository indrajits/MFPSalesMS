/**
 * 
 */
package com.mazdausa.mfpsalesms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mazdausa.mfpsalesms.client.RestClient;
import com.mazdausa.mfpsalesms.helper.RetailSalesHelper;
import com.mazdausa.mfpsalesms.model.response.DealerResponse;

/**
 * @author Indrajit Sen
 *
 */

@Service
public class DealerServiceImpl implements DealerService {
	
	private static final Logger log = LoggerFactory.getLogger(DealerServiceImpl.class);	
	
	@Value("${mfp.dealer-list}")
	private String dealerListUrl;
	
	private RestClient restClient;
	
	private RetailSalesHelper retailSalesHelper;
	
	@Autowired
	public DealerServiceImpl(RestClient restClient, RetailSalesHelper retailSalesHelper) {
		// TODO Auto-generated constructor stub
		this.restClient = restClient;
		this.retailSalesHelper =  retailSalesHelper;
	}
	
	@Override
	public DealerResponse fetchDealerList(String region, String zone, String district,
			String dealer_name, List<String> sortBy) {
		// TODO Auto-generated method stub
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity request = new HttpEntity(headers);
		
		UriComponents builder = retailSalesHelper
				.buildDealerListUri(dealerListUrl, region, zone, district, dealer_name, sortBy);
		
		ResponseEntity<DealerResponse> response = restClient.getRestTemplate()
				.exchange(builder.toUriString(), HttpMethod.GET, request, DealerResponse.class);
		
		return response.getBody();
	}

}
