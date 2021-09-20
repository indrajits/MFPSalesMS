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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

	public void prepareSalesList(List<Dealer> dealerList,
			List<com.mazdausa.mfpsalesms.pojo.RetailSales> retailSalesPojoList,
			List<RetailSales> retailSalesList) {
		Map<String, RetailSales> map = new LinkedHashMap<String, RetailSales>();
		
		for (Dealer dealerElement : dealerList) {
			Dealer dealer = new Dealer();
			dealer.setId(dealerElement.getId());
			dealer.setRegion(dealerElement.getRegion());
			dealer.setZone(dealerElement.getZone());
			dealer.setDistrict(dealerElement.getDistrict());
			dealer.setDlrCode(dealerElement.getDlrCode());
			dealer.setDname(dealerElement.getDname());
			
			List<MonthFeed> monthFeeds = new ArrayList<MonthFeed>();
			
			RetailSales sales = new RetailSales(dealer, monthFeeds);
			
			map.put(dealer.getDlrCode(), sales);
		}
		
		for (com.mazdausa.mfpsalesms.pojo.RetailSales retailSales : retailSalesPojoList) {
			if (map.containsKey(retailSales.getDealer_code())) {
				
				MonthFeed feed = new MonthFeed();
				feed.setYear(Long.toString(retailSales.getYear()));
				feed.setMonth(Integer.toString(retailSales.getMonth()));
				feed.setQty(Integer.toString(retailSales.getQty()));
				
				List<MonthFeed> monthFeeds = map.get(retailSales.getDealer_code()).getMonthFeeds();
				monthFeeds.add(feed);			
				
			}
			
		}		
		
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			retailSalesList.add(map.get(key));
		}
	}
	
	public UriComponents buildDealerListUri(String dealerListUrl, String region, String zone, String district,
			String dealer_name, List<String> sortBy) {
		
		UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(dealerListUrl);
		if (sortBy != null) {
			for (String element : sortBy) {
				componentsBuilder = componentsBuilder.queryParam("sortBy", element);
			}
		}
		
		if (dealer_name != null) {
			if (!dealer_name.isBlank() || !dealer_name.isEmpty()) {
				componentsBuilder = componentsBuilder.queryParam("dealer_name", dealer_name);
			}			
		}
		
		if (region != null) {
			if (!region.isBlank() || !region.isEmpty()) {
				componentsBuilder = componentsBuilder.queryParam("region", region);
			}			
		}
		
		if (zone != null) {
			if (!zone.isBlank() || !zone.isEmpty()) {
				componentsBuilder = componentsBuilder.queryParam("zone", zone);
			}
		}
		
		if (district != null) {
			if (!district.isBlank() || !district.isEmpty()) {
				componentsBuilder = componentsBuilder.queryParam("district", district);
			}
		}
		
		return componentsBuilder.build();
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
