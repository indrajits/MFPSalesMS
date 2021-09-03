/**
 * 
 */
package com.mazdausa.mfpsalesms.repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.mazdausa.mfpsalesms.pojo.CarlineSale;
import com.mazdausa.mfpsalesms.pojo.RetailSales;

/**
 * @author Indrajit Sen
 *
 */
public interface RetailSalesDao {

	public List<RetailSales> findAll(String region, String zone, String district,
			String dealer_name, Map<String, String> sortBy);
	public Integer findMonthSummary(String region, String zone, String district,
			String dealer_name, int year, int month);
	public List<CarlineSale> fetchAllCarlineSales(List<String> carlines, 
			Map<String, String> sortBy, int year,
			Function<List<String>, String> convertCarlineListToString);
}
