package com.cognizant.mfpe.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the bank charge.
 *
 * @return the bank charge
 */
@Getter

/**
 * Sets the bank charge.
 *
 * @param bankCharge the new bank charge
 */
@Setter

/**
 * Instantiates a new process pension input.
 */
@NoArgsConstructor

/**
 * Instantiates a new process pension input.
 *
 * @param aadharNumber the aadhar number
 * @param pensionAmount the pension amount
 * @param bankCharge the bank charge
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
@ApiModel(value = "Model class for Process Pension Input")
public class ProcessPensionInput {
	
	/** The aadhar number. */
	@ApiModelProperty(value = "Aadhar Number of the Pensioner")
	private String aadharNumber;
	
	/** The pension amount. */
	@ApiModelProperty(value = "Pension amount of the Pensioner")
	private double pensionAmount;
	
	/** The bank charge. */
	@ApiModelProperty(value = "Bank charges based on Bank Type")
	private double bankCharge;
}
