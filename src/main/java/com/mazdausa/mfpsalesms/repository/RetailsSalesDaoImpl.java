/**
 * 
 */
package com.mazdausa.mfpsalesms.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
			List<String> sortBy) {
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
		
		if (sortBy!=null && !sortBy.isEmpty()) {
			for (String sort : sortBy) {
				String sortby[] = sort.split("-");
				if (sortby[0].trim().contains("region") ||
						sortby[0].trim().contains("zone_details") || 
						sortby[0].trim().contains("district") || 
						sortby[0].trim().contains("dealer_name")) {
					if (orderBy.length() == 0) {
						orderBy.append(" ORDER BY " + sortby[0] + " " + sortby[1]);
					} else {
						orderBy.append(", " + sortby[0] + " " + sortby[1]);
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

}
