/**
 * 
 */
package com.mazdausa.mfpsalesms.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mazdausa.mfpsalesms.model.request.RetailCarlineSalesRequest;
import com.mazdausa.mfpsalesms.model.response.RetailSalesResponse;
import com.mazdausa.mfpsalesms.model.response.RetailsSalesCarlineResponse;
import com.mazdausa.mfpsalesms.service.RetailSalesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Indrajit Sen
 *
 */

@Api(value="Retail Sales", description="Fetching data pertaining to retail sales")
@RestController
@RequestMapping(value = "/mfp/sales")
public class RetailSalesControllerImpl implements RetailSalesController {

	private RetailSalesService retailSalesService;
	
	@Autowired
	public RetailSalesControllerImpl(RetailSalesService retailSalesService) {
		// TODO Auto-generated constructor stub
		this.retailSalesService = retailSalesService;
	}

	@ApiOperation(value = "Fetch Retail Sales Data", response = RetailSalesResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Retail Sales Data"),
            /*@ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 403, message = ""),*/
            @ApiResponse(code = 404, message = "Fetch Retail Sales Data is not found")
    }
    )
	@Override
	@RequestMapping(value = "/retail-sales", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<RetailSalesResponse> retailSales(@RequestParam(name = "region", required = false) String region, 
			@RequestParam(name = "zone_details", required = false) String zone, 
			@RequestParam(name = "district", required = false) String district, 
			@RequestParam(name = "dealer_name", required = false) String dealer_name,
			@RequestParam(name = "year", required = true) int year,
			@RequestParam(name = "sortBy", required = false) List<String> sortBy) {
		// TODO Auto-generated method stub
		
		RetailSalesResponse retailSalesResponse = 
				new RetailSalesResponse(this.retailSalesService.
						getRetailSalesData(region, zone, district, dealer_name, year, sortBy), 
						this.retailSalesService.getRetailSalesSummary(region, zone, district, dealer_name, year));
		
		ResponseEntity<RetailSalesResponse> responseEntity = 
				new ResponseEntity<RetailSalesResponse>(retailSalesResponse, HttpStatus.OK);
		
		return responseEntity;
	}

	@ApiOperation(value = "Fetch Retail Carline Sales Data", response = RetailsSalesCarlineResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Retail Carline Sales Data"),
            /*@ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 403, message = ""),*/
            @ApiResponse(code = 404, message = "Fetch Retail Carline Sales Data is not found")
    }
    )
	@Override
	@RequestMapping(value = "/retail-carline-sales", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<RetailsSalesCarlineResponse> retailCarlineSales(@RequestBody(required=false) RetailCarlineSalesRequest retailCarlineSalesRequest) {
		// TODO Auto-generated method stub
		RetailsSalesCarlineResponse retailSalesResponse = 
				new RetailsSalesCarlineResponse(this.retailSalesService.
						getRetailCarlineSalesData(retailCarlineSalesRequest));
		
		ResponseEntity<RetailsSalesCarlineResponse> responseEntity = 
				new ResponseEntity<RetailsSalesCarlineResponse>(retailSalesResponse, HttpStatus.OK);
		
		return responseEntity;
	}

	@ApiOperation(value = "Fetch Daily Sales Rate",response = RetailSalesResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Daily Sales Rate"),
            /*@ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 403, message = ""),*/
            @ApiResponse(code = 404, message = "Fetch Daily Sales Rate is not found")
    }
    )
	@Override
	@RequestMapping(value = "/daily-sales-rate", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<RetailSalesResponse> dailySalesRate(@RequestParam(name = "region", required = false) String region, 
			@RequestParam(name = "zone_details", required = false) String zone, 
			@RequestParam(name = "district", required = false) String district,
			@RequestParam(name = "dealer_name", required = false) String dealer_name,
			@RequestParam(name = "year", required = true) int year,
			@RequestParam(name = "sortBy", required = false) List<String> sortBy) {
		// TODO Auto-generated method stub
		RetailSalesResponse retailSalesResponse = 
				new RetailSalesResponse(this.retailSalesService.
						getDailySalesRateData(region, zone, district, dealer_name, year, sortBy), 
						this.retailSalesService.getDailySalesRateSummary(region, zone, district, dealer_name, year));	
		
		
		ResponseEntity<RetailSalesResponse> responseEntity = 
				new ResponseEntity<RetailSalesResponse>(retailSalesResponse, HttpStatus.OK);
		
		return responseEntity;
	}

}
