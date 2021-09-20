/**
 * 
 */
package com.mazdausa.mfpsalesms.repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.mazdausa.mfpsalesms.model.response.Dealer;
import com.mazdausa.mfpsalesms.pojo.CarlineSale;
import com.mazdausa.mfpsalesms.pojo.RetailSales;

/**
 * @author Indrajit Sen
 *
 */
public interface RetailSalesDao {

	public List<RetailSales> findAll(List<Dealer> dealerList);
	public List<RetailSales> findAllDailySalesRate(List<Dealer> dealerList);
	public Integer findMonthSummaryDailySalesRate(List<Dealer> dealerList, int year, int month);	
	public Integer findMonthSummary(List<Dealer> dealerList, int year, int month);
	public List<CarlineSale> fetchAllCarlineSales(List<String> carlines, 
			Map<String, String> sortBy, int year,
			Function<List<String>, String> convertCarlineListToString);
}
