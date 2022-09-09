package com.cognizant.mfpe.model;

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
public class BankDetail {
	
	/** The bank name. */
	private String bankName;
	
	/** The account number. */
	private long accountNumber;
	
	/** The bank type. */
	private String bankType;

	
}
