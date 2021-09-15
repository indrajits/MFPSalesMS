/**
 * 
 */
package com.mazdausa.mfpsalesms.service;

import java.util.List;
import com.mazdausa.mfpsalesms.model.response.DealerResponse;

/**
 * @author Indrajit Sen
 *
 */
public interface DealerService {

	public DealerResponse fetchDealerList(String region, String zone, String district,
			String dealer_name, List<String> sortBy);
	
}
