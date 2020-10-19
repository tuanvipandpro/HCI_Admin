package tuanlm.hr.app.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The Class JwtUtils.
 */
public class JwtUtils {
	
	/** The Constant EXPIRATION_TIME. */
	private static final long EXPIRATION_TIME = 604800000L; //24h
	
	/** The Constant SECRET_KEY_APP. */
	private static final String SECRET_KEY_APP = "TUANLM";
	
	/** The Constant HEADER_STRING. */
	private static final String HEADER_STRING = "Authorization";
	
	/**
	 * Adds the authentication.
	 *
	 * @param res the res
	 * @param username the username
	 */
	public static void addAuthentication(HttpServletResponse res, String username) {		
		res.addHeader(HEADER_STRING, getJwt(username));
	}
	
	/**
	 * Gets the jwt.
	 *
	 * @param username the username
	 * @return the jwt
	 */
	public static String getJwt(String username) {
		Date now = new Date();
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY_APP)
				.compact();
	}
	
	/**
	 * Gets the user from token.
	 *
	 * @param request the request
	 * @return the user from token
	 */
	public static String getUserFromToken(HttpServletRequest request) {
		return (request.getHeader(HEADER_STRING) == null) ? null : Jwts.parser()
											.setSigningKey(SECRET_KEY_APP)
											.parseClaimsJws(request.getHeader(HEADER_STRING).substring(7))
											.getBody()
											.getSubject();
		// End
	}
}
