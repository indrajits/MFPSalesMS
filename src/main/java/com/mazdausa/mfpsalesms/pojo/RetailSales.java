/**
 * 
 */
package com.mazdausa.mfpsalesms.pojo;

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
	private int id;
	private String region;
	private String zone_details;
	private String district;
	private String dealer_code;
	private String dealer_name;
	private String status;
	private int month;
	private long year;
	private int qty;
}
