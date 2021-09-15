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

import com.mazdausa.mfpsalesms.model.response.Dealer;
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
	public List<RetailSales> findAll(List<Dealer> dealerList) {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder("select * from RETAILSALES");
		StringBuilder queryCondition = new StringBuilder();
		//StringBuilder orderBy = new StringBuilder();
		
		if (dealerList != null && !dealerList.isEmpty()) {
			
			StringBuffer dealers = new StringBuffer();
			for (Dealer dealer : dealerList) {
				dealers.append("'" + dealer.getDlrCode().trim() + "', ");
			}
			
			String dealerSting = dealers.substring(0, dealers.length() - 2);
			
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("dealer_code in (" + dealerSting + ")");
			} else {
				queryCondition.append(" and dealer_code in (" + dealerSting + ")");
			}			
		}				
		
		if (!(queryCondition.length() == 0)) {
			query.append(queryCondition);			
		}	
		
		List<RetailSales> retailSales = jdbcTemplate.query(query.toString(), new RetailSalesRowMapper());
		
		return retailSales;
	}

	@Override
	public Integer findMonthSummary(List<Dealer> dealerList, int year, int month) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder("select sum(qty) from RETAILSALES");
		StringBuilder queryCondition = new StringBuilder();
			
		
		if (dealerList != null && !dealerList.isEmpty()) {
			
			StringBuffer dealers = new StringBuffer();
			for (Dealer dealer : dealerList) {
				dealers.append("'" + dealer.getDlrCode().trim() + "', ");
			}
			
			String dealerSting = dealers.substring(0, dealers.length() - 2);
			
			if (queryCondition.length() == 0) {
				queryCondition.append(" where ");
				queryCondition.append("dealer_code in (" + dealerSting + ")");
			} else {
				queryCondition.append(" and dealer_code in (" + dealerSting + ")");
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
