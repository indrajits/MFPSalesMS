/**
 * 
 */
package com.mazdausa.mfpsalesms.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.mazdausa.mfpsalesms.model.MonthFeed;
import com.mazdausa.mfpsalesms.model.RetailSales;
import com.mazdausa.mfpsalesms.model.response.Dealer;
import com.mazdausa.mfpsalesms.pojo.CarlineSale;

/**
 * @author Indrajit Sen
 *
 */
@Component
public class RetailSalesHelper {

	public void prepareRetailSalesList(List<com.mazdausa.mfpsalesms.pojo.RetailSales> retailSalesPojoList,
			List<RetailSales> retailSalesList) {
		Map<String, RetailSales> map = new LinkedHashMap<String, RetailSales>();
		for (com.mazdausa.mfpsalesms.pojo.RetailSales retailSales : retailSalesPojoList) {
			if (map.containsKey(retailSales.getDealer_code())) {
				
				MonthFeed feed = new MonthFeed();
				feed.setYear(Long.toString(retailSales.getYear()));
				feed.setMonth(Integer.toString(retailSales.getMonth()));
				feed.setQty(Integer.toString(retailSales.getQty()));
				
				List<MonthFeed> monthFeeds = map.get(retailSales.getDealer_code()).getMonthFeeds();
				monthFeeds.add(feed);			
				
			} else {
				Dealer dealer = new Dealer();
				dealer.setRegion(retailSales.getRegion());
				dealer.setZone(retailSales.getZone_details());
				dealer.setDistrict(retailSales.getDistrict());
				dealer.setCode(retailSales.getDealer_code());
				dealer.setDname(retailSales.getDealer_name());
				
				MonthFeed feed = new MonthFeed();
				feed.setYear(Long.toString(retailSales.getYear()));
				feed.setMonth(Integer.toString(retailSales.getMonth()));
				feed.setQty(Integer.toString(retailSales.getQty()));
				
				List<MonthFeed> monthFeeds = new ArrayList<MonthFeed>();
				
				monthFeeds.add(feed);
				
				RetailSales sales = new RetailSales(dealer, monthFeeds);
				
				map.put(retailSales.getDealer_code(), sales);				
			}
			
		}		
		
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			retailSalesList.add(map.get(key));
		}
	}
	
	public Function<List<String>, String> convertCarlineModelstoStrings = (carlines) -> {
		StringBuffer carlineModels = new StringBuffer();
		
		String carline = null;
		
		if(carlines != null) {
			for (String model : carlines) {
				carlineModels.append("'" + model + "', ");
			}
			
			if (!carlineModels.isEmpty() && carlineModels.length() > 2) {
				carline = carlineModels.substring(0, carlineModels.length() - 2);
			}
		}				
		
		return carline;
	};
	
	public Function<List<CarlineSale>, Map<String, List<MonthFeed>>> prepareCarlineSalesMap = (carlineSales) -> {
		Map<String, List<MonthFeed>> carlineSalesMap = 
				new LinkedHashMap<String, List<MonthFeed>>();
		for (CarlineSale carlineSale : carlineSales) {
			if (!(carlineSalesMap.containsKey(carlineSale.getCarline()))) {
				List<MonthFeed> monthFeeds = new ArrayList<MonthFeed>();
				monthFeeds.add(carlineSale.getMonthFeed());
				carlineSalesMap.put(carlineSale.getCarline(), monthFeeds);
			} else {
				List<MonthFeed> monthFeeds = carlineSalesMap.get(carlineSale.getCarline());
				monthFeeds.add(carlineSale.getMonthFeed());
				carlineSalesMap.put(carlineSale.getCarline(), monthFeeds);
			}
		}		
		
		return carlineSalesMap;
	};
}
