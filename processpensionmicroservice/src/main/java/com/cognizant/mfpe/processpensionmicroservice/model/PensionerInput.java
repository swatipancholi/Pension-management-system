package com.cognizant.mfpe.processpensionmicroservice.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the pension type.
 *
 * @return the pension type
 */

/**
 * Gets the pension type.
 *
 * @return the pension type
 */
@Getter

/**
 * Sets the pension type.
 *
 * @param pensionType the new pension type
 */

/**
 * Sets the pension type.
 *
 * @param pensionType the new pension type
 */
@Setter

/**
 * Instantiates a new pensioner input.
 */

/**
 * Instantiates a new pensioner input.
 */
@NoArgsConstructor

/**
 * Instantiates a new pensioner input.
 *
 * @param name the name
 * @param dateOfBirth the date of birth
 * @param pan the pan
 * @param aadharNumber the aadhar number
 * @param pensionType the pension type
 */

/**
 * Instantiates a new pensioner input.
 *
 * @param pensionerName the pensioner name
 * @param pensionerDob the pensioner dob
 * @param pan the pan
 * @param aadharNumber the aadhar number
 * @param pensionType the pension type
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

/**
 * Hash code.
 *
 * @return the int
 */

/**
 * Hash code.
 *
 * @return the int
 */
@EqualsAndHashCode
@ApiModel(description = "Model class for Pensioner Input")
public class PensionerInput {

	/** The name. */
	@ApiModelProperty(value = "Name of the Pensioner")
	private String pensionerName;

	/** The date of birth. */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "Date of birth of the Pensioner")
	private LocalDate pensionerDob;

	/** The pan. */
	@ApiModelProperty(value = "PAN number of the Pensioner")
	private String pan;

	/** The aadhar number. */
	@ApiModelProperty(value = "Aadhar number of the Pensioner")
	private String aadharNumber;

	/** The pension type. */
	@ApiModelProperty(value = "Type of pension self/family")
	private String pensionType;

}
