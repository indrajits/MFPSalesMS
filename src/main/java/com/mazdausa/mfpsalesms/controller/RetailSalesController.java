/**
 * 
 */
package com.mazdausa.mfpsalesms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mazdausa.mfpsalesms.model.request.RetailCarlineSalesRequest;
import com.mazdausa.mfpsalesms.model.response.RetailSalesResponse;
import com.mazdausa.mfpsalesms.model.response.RetailsSalesCarlineResponse;

/**
 * @author Indrajit Sen
 *
 */

public interface RetailSalesController {
		
	public ResponseEntity<RetailSalesResponse> retailSales(String region, String zone, String district, String dealer_name, int year, List<String> sortBy);
	public ResponseEntity<RetailsSalesCarlineResponse> retailCarlineSales(RetailCarlineSalesRequest retailCarlineSalesRequest);
	public ResponseEntity<RetailSalesResponse> dailySalesRate(String region, String zone, String district, String dealer_name, int year, List<String> sortBy);
}
