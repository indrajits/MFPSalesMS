/**
 * 
 */
package com.mazdausa.mfpsalesms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mazdausa.mfpsalesms.pojo.RetailSales;

/**
 * @author Indrajit Sen
 *
 */
public class RetailSalesRowMapper implements RowMapper<RetailSales> {

	@Override
	public RetailSales mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		RetailSales retailSales = new RetailSales();
		retailSales.setId(rs.getInt("id"));
		retailSales.setRegion(rs.getString("region"));
		retailSales.setZone_details(rs.getString("zone_details"));
		retailSales.setDistrict(rs.getString("district"));
		retailSales.setDealer_code(rs.getString("dealer_code"));
		retailSales.setDealer_name(rs.getString("dealer_name"));
		retailSales.setMonth(rs.getInt("month"));
		retailSales.setYear(rs.getInt("year"));
		retailSales.setQty(rs.getInt("qty"));
		return retailSales;
	}

}
