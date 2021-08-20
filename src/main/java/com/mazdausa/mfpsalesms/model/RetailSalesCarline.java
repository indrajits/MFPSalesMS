/**
 * 
 */
package com.mazdausa.mfpsalesms.model;

import java.util.List;

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
public class RetailSalesCarline {

	@ApiModelProperty(notes = "Carline Detail")
	private String Carline;
	@ApiModelProperty(notes = "Monthly Feed")
	private List<MonthFeed> monthFeeds;
}
