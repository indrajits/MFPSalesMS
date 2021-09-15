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
public class DealerResponse {
	
	@ApiModelProperty(notes = "Dealer List")
	private Iterable<Dealer> dealerBeanList;

}
