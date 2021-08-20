/**
 * 
 */
package com.mazdausa.mfpsalesms.model.response;

import java.util.List;

import com.mazdausa.mfpsalesms.model.MonthFeed;
import com.mazdausa.mfpsalesms.model.RetailSales;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Indrajit Sen
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetailSalesResponse {

	@ApiModelProperty(notes = "Retail Sales List")
	private List<RetailSales> retailSales;
	@ApiModelProperty(notes = "Retail Sales Summary")
	private List<MonthFeed> retailSalesSummary;
}
