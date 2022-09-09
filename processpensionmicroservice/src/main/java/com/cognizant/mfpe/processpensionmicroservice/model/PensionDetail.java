package com.cognizant.mfpe.processpensionmicroservice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the pension amount.
 *
 * @return the pension amount
 */

/**
 * Gets the pension amount.
 *
 * @return the pension amount
 */
@Getter

/**
 * Sets the pension amount.
 *
 * @param pensionAmount the new pension amount
 */

/**
 * Sets the pension amount.
 *
 * @param pensionAmount the new pension amount
 */
@Setter

/**
 * Instantiates a new pension detail.
 */

/**
 * Instantiates a new pension detail.
 */
@NoArgsConstructor

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
@Entity
@ApiModel(description = "Model class for Pension Details")
public class PensionDetail {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "Id of the Pensioner")
	private Long id;

	/** The name. */
	@ApiModelProperty(value = "Name of the Pensioner")
	private String pensionerName;

	/** The date of birth. */
	@ApiModelProperty(value = "Date of birth of the Pensioner")
	private LocalDate pensionerDob;

	/** The pan. */
	@Column(unique = true)
	@ApiModelProperty(value = "PAN number of the Pensioner")
	private String pan;

	/** The pension type. */
	@ApiModelProperty(value = "Type of pension self/family")
	private String pensionType;

	/** The pension amount. */
	@ApiModelProperty(value = "Total Pension")
	private double pensionAmount;

	/**
	 * Instantiates a new pension detail.
	 *
	 * @param name the name
	 * @param date the date of birth
	 * @param pan the pan
	 * @param pensionType the pension type
	 * @param pensionAmount the pension amount
	 */
	public PensionDetail(String name, LocalDate date, String pan, String pensionType, double pensionAmount) {
		super();
		this.pensionerName = name;
		this.pensionerDob = date;
		this.pan = pan;
		this.pensionType = pensionType;
		this.pensionAmount = pensionAmount;
	}


}
