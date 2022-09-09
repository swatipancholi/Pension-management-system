package com.cognizant.mfpe.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Gets the password.
 *
 * @return the password
 */
@Getter

/**
 * Sets the password.
 *
 * @param password the new password
 */
@Setter

/**
 * Instantiates a new user login.
 */
@NoArgsConstructor

/**
 * Instantiates a new user login.
 *
 * @param userName the user name
 * @param password the password
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString
@Entity
@ApiModel(description = "Model class for User Login")
public class UserLogin {

	/** The user name. */
	@Id
	@ApiModelProperty(value = "Username of the Pensioner")
	private String userName;

	/** The password. */
	@ApiModelProperty(value = "Password of the Pensioner")
	private String password;

}