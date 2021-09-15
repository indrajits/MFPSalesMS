/**
 * 
 */
package com.mazdausa.mfpsalesms.model.response;

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
public class Dealer {

	@ApiModelProperty(notes = "id")
	private Long id;
	@ApiModelProperty(notes = "Dealer Name")
	private String dname;
	@ApiModelProperty(notes = "Dealer Code")
	private String dlrCode;
	@ApiModelProperty(notes = "Region Details")
	private String region;
	@ApiModelProperty(notes = "Zone Details")
	private String zone;
	@ApiModelProperty(notes = "District Details")
	private String district;
	
}
