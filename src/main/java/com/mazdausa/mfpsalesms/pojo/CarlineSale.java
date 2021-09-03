/**
 * 
 */
package com.mazdausa.mfpsalesms.pojo;

import com.mazdausa.mfpsalesms.model.MonthFeed;

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
public class CarlineSale {

	@ApiModelProperty(notes = "Carline Detail")
	private String Carline;
	@ApiModelProperty(notes = "Monthly Feed")
	private MonthFeed monthFeed;
}
