/**
 * 
 */
package com.mazdausa.mfpsalesms.model;

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
public class MonthFeed {
	
	@ApiModelProperty(notes = "Month Details")
	private String month;
	@ApiModelProperty(notes = "Year Details")
	private String year;
	@ApiModelProperty(notes = "Quantity Details")
	private String qty;

}
