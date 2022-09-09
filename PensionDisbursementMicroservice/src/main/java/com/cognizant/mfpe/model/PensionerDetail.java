package com.cognizant.mfpe.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the bank.
 *
 * @return the bank
 */
@Getter

/**
 * Sets the bank.
 *
 * @param bank the new bank
 */
@Setter

/**
 * Instantiates a new pensioner detail.
 */
@NoArgsConstructor

/**
 * Instantiates a new pensioner detail.
 *
 * @param pensionerName the pensioner name
 * @param pensionerDob the pensioner dob
 * @param pan the pan
 * @param salary the salary
 * @param allowance the allowance
 * @param pensionType the pension type
 * @param bank the bank
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
@ApiModel(description = "Model class for Pensioner Details")
public class PensionerDetail {

//	@ApiModelProperty(value = "Aadhar Number of the Pensioner")
/** The pensioner name. */
	@ApiModelProperty(value = "Name of the Pensioner")
	private String pensionerName;
	
	/** The pensioner dob. */
	@ApiModelProperty(value = "DOB of the Pensioner")
	private Date pensionerDob;
	
	/** The pan. */
	@ApiModelProperty(value = "PAN no. of the Pensioner")
	private String pan;
	
	/** The salary. */
	@ApiModelProperty(value = "Slary Earned by the Pensioner")
	private double salary;
	
	/** The allowance. */
	@ApiModelProperty(value = "Allowances of the Pensioner")
	private double allowance;
	
	/** The pension type. */
	@ApiModelProperty(value = "Type of Pension")
	private String pensionType;
	
	/** The bank. */
	@ApiModelProperty(value = "Bank Details of the Pensioner")
	private BankDetail bank;

}
