/**
 * 
 */
package com.mazdausa.mfpsalesms.repository;

import java.util.List;

import com.mazdausa.mfpsalesms.pojo.RetailSales;

/**
 * @author Indrajit Sen
 *
 */
public interface RetailSalesDao {

	public List<RetailSales> findAll(String region, String zone, String district,
			String dealer_name, List<String> sortBy);
	public Integer findMonthSummary(String region, String zone, String district,
			String dealer_name, int year, int month);
}
