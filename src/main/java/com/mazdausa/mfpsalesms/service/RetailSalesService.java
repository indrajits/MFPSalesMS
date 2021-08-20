/**
 * 
 */
package com.mazdausa.mfpsalesms.service;

import java.util.List;

import com.mazdausa.mfpsalesms.model.MonthFeed;
import com.mazdausa.mfpsalesms.model.RetailSales;
import com.mazdausa.mfpsalesms.model.RetailSalesCarline;
import com.mazdausa.mfpsalesms.model.request.RetailCarlineSalesRequest;

/**
 * @author Indrajit Sen
 *
 */
public interface RetailSalesService {
	
	public List<RetailSales> getRetailSalesData(String region, String zone, String district, String dealer_name, int year, List<String> sortBy);
	public List<MonthFeed> getRetailSalesSummary(String region, String zone, String district, String dealer_name, int year);
	public List<RetailSalesCarline> getRetailCarlineSalesData(RetailCarlineSalesRequest retailCarlineSalesRequest);
	public List<RetailSales> getDailySalesRateData(String region, String zone, String district, String dealer_name, int year, List<String> sortBy);
	public List<MonthFeed> getDailySalesRateSummary(String region, String zone, String district, String dealer_name, int year);
}
