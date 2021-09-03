/**
 * 
 */
package com.mazdausa.mfpsalesms.repository;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mazdausa.mfpsalesms.pojo.CarlineSale;
import com.mazdausa.mfpsalesms.pojo.RetailSales;

/**
 * @author Indrajit Sen
 *
 */
@Repository
public class RetailsSalesDaoImpl implements RetailSalesDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RetailsSalesDaoImpl(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<RetailSales> findAll(String region, String zone, String district, String dealer_name,
			Map<String, String> sortBy) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder("select * from RETAILSALES");
		StringBuilder queryCondition = new StringBuilder();
		StringBuilder orderBy = new StringBuilder();
		
		if (region!=null && !region.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("region=" + "'" + region.trim() + "'");
			} else {
				queryCondition.append(" and region=" + "'" + region.trim() + "'");
			}			
		}
		if (zone!=null && !zone.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("zone_details=" + "'" + zone.trim() + "'");
			} else {
				queryCondition.append(" and zone_details=" + "'" + zone.trim() + "'");
			}			
		}
		if (district!=null && !district.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("district=" + "'" + district.trim() + "'");
			} else {
				queryCondition.append(" and district=" + "'" + district.trim() + "'");
			}			
		}
		if (dealer_name!=null && !dealer_name.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("dealer_name=" + "'" + dealer_name.trim() + "'");
			} else {
				queryCondition.append(" and dealer_name=" + "'" + dealer_name.trim() + "'");
			}			
		}
		
		if (sortBy != null && !sortBy.isEmpty()) {
			Set<String> keyset = sortBy.keySet();
			for (String columnName : keyset) {
				if (columnName.trim().contains("region") ||
						columnName.trim().contains("zone_details") || 
						columnName.trim().contains("district") || 
						columnName.trim().contains("dealer_name")) {
					if (orderBy.length() == 0) {
						orderBy.append(" ORDER BY " + columnName.trim() + " " + sortBy.get(columnName));
					} else {
						orderBy.append(", " + columnName.trim() + " " + sortBy.get(columnName));
					}
				}
			}
		}
				
		
		if (!(queryCondition.length() == 0)) {
			query.append(queryCondition);			
		}
		
		if (!(orderBy.length() == 0)) {
			query.append(orderBy);
		}		
		
		List<RetailSales> retailSales = jdbcTemplate.query(query.toString(), new RetailSalesRowMapper());
		
		return retailSales;
	}

	@Override
	public Integer findMonthSummary(String region, String zone, String district,
			String dealer_name, int year, int month) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder("select sum(qty) from RETAILSALES");
		StringBuilder queryCondition = new StringBuilder();
		
		if (region!=null && !region.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("region=" + "'" + region.trim() + "'");
			} else {
				queryCondition.append(" and region=" + "'" + region.trim() + "'");
			}			
		}
		if (zone!=null && !zone.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("zone_details=" + "'" + zone.trim() + "'");
			} else {
				queryCondition.append(" and zone_details=" + "'" + zone.trim() + "'");
			}			
		}
		if (district!=null && !district.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("district=" + "'" + district.trim() + "'");
			} else {
				queryCondition.append(" and district=" + "'" + district.trim() + "'");
			}			
		}
		if (dealer_name!=null && !dealer_name.trim().isEmpty()) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("dealer_name=" + "'" + dealer_name.trim() + "'");
			} else {
				queryCondition.append(" and dealer_name=" + "'" + dealer_name.trim() + "'");
			}			
		}
		if (year > 0) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("year=" + year);
			} else {
				queryCondition.append(" and year=" + year);
			}			
		}
		if (month > 0 && month < 13) {
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("month=" + month);
			} else {
				queryCondition.append(" and month=" + month);
			}			
		}
		
		if (!(queryCondition.length() == 0)) {
			query.append(queryCondition);			
		}
		
		Integer monthSummary = jdbcTemplate.queryForObject(query.toString(), Integer.class);
		
		return monthSummary;
	}

	@Override
	public List<CarlineSale> fetchAllCarlineSales(List<String> carlines, 
			Map<String, String> sortBy, int year,
			Function<List<String>, String> convertCarlineListToString) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder("select * from CarlineSales");
		StringBuilder queryCondition = new StringBuilder();
		StringBuilder orderBy = new StringBuilder();
				
		String carlineModels = convertCarlineListToString.apply(carlines);
		if (year == 0) {
			queryCondition.append(" where year = " + Calendar.getInstance().get(Calendar.YEAR));
		} else {
			queryCondition.append(" where year = " + year);
		}
		
		if (carlineModels != null && !carlineModels.trim().isEmpty() && !carlineModels.trim().equals("all")) {			
			queryCondition.append(" and carline in " + "(" + carlineModels.trim() + ")");					
		}
		
		if (sortBy != null && !sortBy.isEmpty()) {
			Set<String> keyset = sortBy.keySet();
			for (String carline : keyset) {
				if (carline.trim().contains("carline")) {
					if (orderBy.length() == 0) {
						orderBy.append(" ORDER BY " + carline.trim() + " " + sortBy.get(carline));
					} else {
						orderBy.append(", " + carline.trim() + " " + sortBy.get(carline));
					}
				}
			}
		}
		
		if (!(queryCondition.length() == 0)) {
			query.append(queryCondition);			
		}
		
		if (!(orderBy.length() == 0)) {
			query.append(orderBy);
		}
		
		List<CarlineSale> carlineSales = jdbcTemplate.query(query.toString(), new CarlineSalesRowMapper());
		
		return carlineSales;
	}

}
