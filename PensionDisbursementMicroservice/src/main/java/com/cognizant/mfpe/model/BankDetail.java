package com.cognizant.mfpe.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the bank type.
 *
 * @return the bank type
 */
@Getter

/**
 * Sets the bank type.
 *
 * @param bankType the new bank type
 */
@Setter

/**
 * Instantiates a new bank detail.
 */
@NoArgsConstructor

/**
 * Instantiates a new bank detail.
 *
 * @param bankName the bank name
 * @param accountNumber the account number
 * @param bankType the bank type
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
@ApiModel(description = "Model class for Bank Details")
public class BankDetail {

	/** The bank name. */
	@ApiModelProperty(value = "Name of the Bank")
	private String bankName;

	/** The account number. */
	@ApiModelProperty(value = "Account Number of the Customer")
	private String accountNumber;

	/** The bank type. */
	@ApiModelProperty(value = "Customer Account Type")
	private String bankType;
}
