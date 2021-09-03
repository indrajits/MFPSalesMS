package com.mazdausa.mfpsalesms.model.response;

import java.util.List;
import java.util.Map;

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
public class RetailsSalesCarlineResponse {

	@ApiModelProperty(notes = "Retail Carline Sales List")
	private Map<String, List<MonthFeed>> retailSalesCarline;
}
