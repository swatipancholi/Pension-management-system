package com.cognizant.mfpe.model;



import java.time.LocalDate;

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
public class PensionerDetail {
	
	/** The pensioner name. */
	private String pensionerName;
	
	/** The pensioner dob. */
	private LocalDate pensionerDob;
	
	/** The pan. */
	private String pan;
	
	/** The salary. */
	private double salary;
	
	/** The allowance. */
	private double allowance;
	
	/** The pension type. */
	private String pensionType;
	
	/** The bank. */
	private BankDetail bank;

	
}


