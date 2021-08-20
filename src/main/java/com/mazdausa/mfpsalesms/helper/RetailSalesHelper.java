/**
 * 
 */
package com.mazdausa.mfpsalesms.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.mazdausa.mfpsalesms.model.MonthFeed;
import com.mazdausa.mfpsalesms.model.RetailSales;
import com.mazdausa.mfpsalesms.model.response.Dealer;

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
}
