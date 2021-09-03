/**
 * 
 */
package com.mazdausa.mfpsalesms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mazdausa.mfpsalesms.model.MonthFeed;
import com.mazdausa.mfpsalesms.pojo.CarlineSale;

/**
 * @author Indrajit Sen
 *
 */
public class CarlineSalesRowMapper implements RowMapper<CarlineSale> {

	@Override
	public CarlineSale mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		CarlineSale carlineSale = new CarlineSale();
		MonthFeed monthFeed = new MonthFeed();
		
		monthFeed.setYear(Integer.toString(rs.getInt("year")));
		monthFeed.setMonth(Integer.toString(rs.getInt("month")));
		monthFeed.setQty(Integer.toString(rs.getInt("qty")));
		
		carlineSale.setCarline(rs.getString("carline"));
		carlineSale.setMonthFeed(monthFeed);
		
		return carlineSale;
	}

}
