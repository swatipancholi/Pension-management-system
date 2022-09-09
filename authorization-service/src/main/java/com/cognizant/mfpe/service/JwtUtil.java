package com.cognizant.mfpe.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class JwtUtil.
 */
@Service

/** The Constant log. */
@Slf4j
public class JwtUtil {

	/** The secret key. */
	@Value("${app.secret}")
	private String secretKey;

	/** The expiration time. */
	@Value("${app.expiry}")
	private int expirationTime;

	/**
	 * Extract username.
	 *
	 * @param token the token
	 * @return the string
	 */
	public String extractUsername(String token) {
		log.info("BEGIN   -   [extractUsername(token)]");
		String userName = extractClaim(token, Claims::getSubject);
		log.debug("Username" + userName);
		log.info("END   -   [extractUsername(token)]");
		return userName;
	}

	/**
	 * Extract expiration.
	 *
	 * @param token the token
	 * @return the date
	 */
	public Date extractExpiration(String token) {
		log.info("BEGIN   -   [extractExpiration(token)]");
		Date date = extractClaim(token, Claims::getExpiration);
		log.debug("Date" + date);
		log.info("END   -   [extractUsername(token)]");
		return date;
	}

	/**
	 * Extract claim.
	 *
	 * @param <T> the generic type
	 * @param token the token
	 * @param claimsResolver the claims resolver
	 * @return the t
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.info("BEGIN   -   [extractClaims()]");
		final Claims claims = extractAllClaims(token);
		log.debug("Claims" + claims);
		log.info("END   -   [extractClaims()]");
		return claimsResolver.apply(claims);
	}

	/**
	 * Extract all claims.
	 *
	 * @param token the token
	 * @return the claims
	 */
	private Claims extractAllClaims(String token) {
		log.info("BEGIN   -   [extractAllClaims(token)]");
		Claims claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		log.debug("Claims" + claim);
		log.info("END   -   [extractAllClaims()]");
		return claim;
	}

	/**
	 * Checks if is token expired.
	 *
	 * @param token the token
	 * @return the boolean
	 */
	private Boolean isTokenExpired(String token) {
		log.info("BEGIN   -   [isTokenExpired(token)]");
		boolean bool = extractExpiration(token).before(new Date());
		log.debug("Boolean" + bool);
		log.info("END   -   [isTokenExpired(token)]");
		return bool;
	}

	/**
	 * Generate token.
	 *
	 * @param userDetails the user details
	 * @return the string
	 */
	public String generateToken(UserDetails userDetails) {
		log.info("BEGIN   -   [generateToken(userDetails)]");
		Map<String, Object> claims = new HashMap<>();
		log.debug("CLaims" + claims);
		String token = createToken(claims, userDetails.getUsername());
		log.debug("Token" + token);
		log.info("END   -   [generateToken(userDetails)]");
		return token;
	}

	/**
	 * Creates the token.
	 *
	 * @param claims the claims
	 * @param subject the subject
	 * @return the string
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		log.info("BEGIN   -   [createToken()]");
		String token = Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		log.debug("Token " + token);
		log.info("END   -   [createToken()]");
		return token;
	}

	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @param userDetails the user details
	 * @return the boolean
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		log.info("BEGIN   -   [validateToken(token,userDetails)]");
		final String username = extractUsername(token);
		log.debug("Username " + username);
		boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		log.debug("isValid" + isValid);
		log.info("END   -   [validateToken(token,userDetails)]");
		return isValid;
	}

}