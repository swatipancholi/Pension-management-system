package com.cognizant.mfpe.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the auth token.
 *
 * @return the auth token
 */
@Getter

/**
 * Sets the auth token.
 *
 * @param authToken the new auth token
 */
@Setter

/**
 * Instantiates a new user token.
 */
@NoArgsConstructor

/**
 * Instantiates a new user token.
 *
 * @param userName the user name
 * @param authToken the auth token
 */
@AllArgsConstructor

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
@EqualsAndHashCode
@ApiModel(description = "Model class for User Token")
public class UserToken {
	
	/** The user name. */
	@ApiModelProperty(value = "Username of the Pensioner")
	private String userName;

	/** The auth token. */
	@ApiModelProperty(value = "Authorization token of the User")
	private String authToken;

}
