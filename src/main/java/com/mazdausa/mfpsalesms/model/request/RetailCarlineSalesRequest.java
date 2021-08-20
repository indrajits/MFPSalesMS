/**
 * 
 */
package com.mazdausa.mfpsalesms.model.request;

import java.util.List;
import java.util.Map;

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
public class RetailCarlineSalesRequest {

	@ApiModelProperty(notes = "Region Details")
	private String region;
	@ApiModelProperty(notes = "Zone Details")
	private String zone;
	@ApiModelProperty(notes = "District Details")
	private String district;
	@ApiModelProperty(notes = "Year Details")
	private String year;
	@ApiModelProperty(notes = "Sorting Details")
	private Map<String, String> sortby;
	@ApiModelProperty(notes = "Carline List")
	private List<String> carlines;
	
}
