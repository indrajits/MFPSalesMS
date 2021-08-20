/**
 * 
 */
package com.mazdausa.mfpsalesms.model;

import java.util.List;

import com.mazdausa.mfpsalesms.model.response.Dealer;

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
public class RetailSales {
	
	@ApiModelProperty(notes = "Dealer Details")
	private Dealer dealer;
	@ApiModelProperty(notes = "Monthly Feeds")
	private List<MonthFeed> monthFeeds;
}
