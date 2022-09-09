package com.cognizant.mfpe.processpensionmicroservice.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the bank detail.
 *
 * @return the bank detail
 */

/**
 * Gets the bank.
 *
 * @return the bank
 */
@Getter

/**
 * Sets the bank detail.
 *
 * @param bankDetail the new bank detail
 */

/**
 * Sets the bank.
 *
 * @param bank the new bank
 */
@Setter

/**
 * Instantiates a new pensioner detail.
 */

/**
 * Instantiates a new pensioner detail.
 */
@NoArgsConstructor

/**
 * Instantiates a new pensioner detail.
 *
 * @param aadhaarNumber the aadhaar number
 * @param pensionerName the pensioner name
 * @param pensionerDob the pensioner dob
 * @param pensionerPAN the pensioner PAN
 * @param salaryEarned the salary earned
 * @param allowances the allowances
 * @param pensionType the pension type
 * @param bankDetail the bank detail
 */

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

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
@ApiModel(description = "Model class for Pensioner Details")
public class PensionerDetail {
	
	
	/** The pensioner name. */
	@ApiModelProperty(value = "Name of the Pensioner")
	private String pensionerName;
	
	/** The pensioner dob. */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "DOB of the Pensioner")
	private LocalDate pensionerDob;
	
	/** The pensioner PAN. */
	@ApiModelProperty(value = "PAN no. of the Pensioner")
	private String pan;
	
	/** The salary earned. */
	@ApiModelProperty(value = "Salary Earned by the Pensioner")
	private double salary;
	
	/** The allowances. */
	@ApiModelProperty(value = "Allowances of the Pensioner")
	private double allowance;
	
	/** The pension type. */
	@ApiModelProperty(value = "Type of pension self/family")
	private String pensionType;
	
	/** The bank detail. */
	@ApiModelProperty(value = "Bank Details of the Pensioner")
	private BankDetail bank;
	
	
}
