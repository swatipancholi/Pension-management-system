package com.cognizant.mfpe.model;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the process pension status code.
 *
 * @return the process pension status code
 */
@Getter

/**
 * Sets the process pension status code.
 *
 * @param processPensionStatusCode the new process pension status code
 */
@Setter

/**
 * Instantiates a new process pension response.
 */
@NoArgsConstructor

/**
 * Instantiates a new process pension response.
 *
 * @param processPensionStatusCode the process pension status code
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
@ApiModel(value = "Model Class for Process Pension Response")
public class ProcessPensionResponse {
	
	/** The process pension status code. */
	private Integer processPensionStatusCode;
}