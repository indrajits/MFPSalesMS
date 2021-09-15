/**
 * 
 */
package com.mazdausa.mfpsalesms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mazdausa.mfpsalesms.helper.RetailSalesHelper;
import com.mazdausa.mfpsalesms.model.MonthFeed;
import com.mazdausa.mfpsalesms.model.RetailSales;
import com.mazdausa.mfpsalesms.model.request.RetailCarlineSalesRequest;
import com.mazdausa.mfpsalesms.model.response.Dealer;
import com.mazdausa.mfpsalesms.pojo.CarlineSale;
import com.mazdausa.mfpsalesms.repository.RetailSalesDao;

/**
 * @author Indrajit Sen
 *
 */
@Component
public class RetailSalesServiceImpl implements RetailSalesService {

	private RetailSalesDao retailSalesDao;
	private RetailSalesHelper retailSalesHelper;
	private DealerService dealerService;
	
	@Autowired
	public RetailSalesServiceImpl(RetailSalesDao retailSalesDao, RetailSalesHelper retailSalesHelper, DealerService dealerService) {
		// TODO Auto-generated constructor stub
		this.retailSalesDao = retailSalesDao;
		this.retailSalesHelper = retailSalesHelper;
		this.dealerService = dealerService;
	}
	
	@Override
	public List<RetailSales> getRetailSalesData(String region, String zone, String district,
			String dealer_name, int year, List<String> sortBy) {
		// TODO Auto-generated method stub
				
		List<Dealer> dealers = (List<Dealer>) this.dealerService.fetchDealerList(region, zone, district,
				dealer_name, sortBy).getDealerBeanList();
		
		List<com.mazdausa.mfpsalesms.pojo.RetailSales> retailSalesPojoList = 
				(List<com.mazdausa.mfpsalesms.pojo.RetailSales>) retailSalesDao.findAll(dealers);
		
		List<RetailSales> retailSalesList = new ArrayList<RetailSales>();
		retailSalesHelper.prepareRetailSalesList(dealers, retailSalesPojoList, retailSalesList);	
		
		return retailSalesList;
	}
	
	@Override
	public List<MonthFeed> getRetailSalesSummary(String region, String zone, 
			String district, String dealer_name, int year) {
		// TODO Auto-generated method stub		
		List<MonthFeed> monthFeedList = new ArrayList<MonthFeed>();
		Integer monthSummary = null;
		String qty = null;
		MonthFeed monthFeed = null;
		for (int i = 0; i < 12; i++) {
			
			List<Dealer> dealers = (List<Dealer>) this.dealerService.fetchDealerList(region, zone, district,
					dealer_name, null).getDealerBeanList();
						
			monthSummary = retailSalesDao.findMonthSummary(dealers, year, i+1);
			if (monthSummary == null) {
				qty = null;
			} else {
				qty = Integer.toString(monthSummary);
			}
			monthFeed = new MonthFeed(Integer.toString(i+1), 
					Integer.toString(year), 
					qty);					
			monthFeedList.add(monthFeed);	
		}		
		return monthFeedList;
	}

	@Override
	public Map<String, List<MonthFeed>> getRetailCarlineSalesData(RetailCarlineSalesRequest retailCarlineSalesRequest) {
		// TODO Auto-generated method stub
		List<CarlineSale> carlineSales = null;
		
		if (retailCarlineSalesRequest == null || 
				(retailCarlineSalesRequest.getCarlines() == null && 
				retailCarlineSalesRequest.getSortby() == null && 
				retailCarlineSalesRequest.getYear() == 0)) {
			carlineSales = this.retailSalesDao.
					fetchAllCarlineSales(null, 
					null, Calendar.getInstance().get(Calendar.YEAR),
					this.retailSalesHelper.convertCarlineModelstoStrings);
		} else {
			carlineSales = this.retailSalesDao.
					fetchAllCarlineSales(retailCarlineSalesRequest.getCarlines(), 
					retailCarlineSalesRequest.getSortby(), 
					retailCarlineSalesRequest.getYear(), 
					this.retailSalesHelper.convertCarlineModelstoStrings);
		}
		
		Map<String, List<MonthFeed>> carlineSalesMap = 
				this.retailSalesHelper.prepareCarlineSalesMap.apply(carlineSales);	
		
		return carlineSalesMap;
	}

	@Override
	public List<RetailSales> getDailySalesRateData(String region, String zone, String district,
			String dealer_name, int year, List<String> sortBy) {
		// TODO Auto-generated method stub
				
		List<Dealer> dealers = (List<Dealer>) this.dealerService.fetchDealerList(region, zone, district,
				dealer_name, sortBy).getDealerBeanList();
		
		List<com.mazdausa.mfpsalesms.pojo.RetailSales> retailSalesPojoList = 
				(List<com.mazdausa.mfpsalesms.pojo.RetailSales>) retailSalesDao.findAll(dealers);
		
		List<RetailSales> retailSalesList = new ArrayList<RetailSales>();
		retailSalesHelper.prepareRetailSalesList(dealers, retailSalesPojoList, retailSalesList);
		
		return retailSalesList;
	}

	@Override
	public List<MonthFeed> getDailySalesRateSummary(String region, String zone, String district, String dealer_name,
			int year) {
		// TODO Auto-generated method stub
		List<MonthFeed> monthFeedList = new ArrayList<MonthFeed>();
		Integer monthSummary = null;
		String qty = null;
		MonthFeed monthFeed = null;
		for (int i = 0; i < 12; i++) {
			
			List<Dealer> dealers = (List<Dealer>) this.dealerService.fetchDealerList(region, zone, district,
					dealer_name, null).getDealerBeanList();
			
			monthSummary = retailSalesDao.findMonthSummary(dealers, year, i+1);
			if (monthSummary == null) {
				qty = null;
			} else {
				qty = Integer.toString(monthSummary);
			}
			monthFeed = new MonthFeed(Integer.toString(i+1), 
					Integer.toString(year), 
					qty);					
			monthFeedList.add(monthFeed);	
		}		
		return monthFeedList;
	}	

}
