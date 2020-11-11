package tuanlm.hr.app.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tuanlm.hr.app.models.request.GmailRequest;
import tuanlm.hr.app.models.request.LoginRequest;
import tuanlm.hr.app.models.response.LoginResponse;
import tuanlm.hr.app.service.AccountService;
import tuanlm.hr.app.utils.AppConstants;

/**
 * The Class LoginController.
 */
@RestController
@RequestMapping("/api/authenticate")
@AllArgsConstructor
@Slf4j
public class LoginController {
	
	/** The authentication manager. */
	private AuthenticationManager authenticationManager;
	
	/** The account service. */
	private AccountService accountService;
	
	/**
	 * Login.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(summary = "Api Login Admin (Web)")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
		// Check Login
		SecurityContextHolder.getContext().setAuthentication(authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())));
		
		LoginResponse response = accountService.getLoginResponse(request.getUsername(), AppConstants.LOGIN_ADMIN);
		
		return (response == null) ? new ResponseEntity<LoginResponse>(HttpStatus.UNAUTHORIZED)  : new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}
	
	/**
	 * Login for mobile.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(summary = "Api Login User (Mobile)")
	@PostMapping("/login-for-mobile")	
	public ResponseEntity<LoginResponse> loginForMobile(@RequestBody @Valid LoginRequest request) {
		// Check Login
		SecurityContextHolder.getContext().setAuthentication(authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())));
		
		return new ResponseEntity<LoginResponse>(accountService.getLoginResponse(request.getUsername(), AppConstants.LOGIN_MEMBER), HttpStatus.OK);
	}
	
	/**
	 * Login by gmail.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(summary = "Api Login User By Gmail (Mobile)")
	@PostMapping("/login-by-gmail")	
	public ResponseEntity<LoginResponse> loginByGmail(@RequestBody @Valid GmailRequest request) {
		try {
			return new ResponseEntity<LoginResponse>(accountService.loginByEmail(request), HttpStatus.OK);
		}
		catch(Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<LoginResponse>(HttpStatus.FORBIDDEN);
		}
	}
}
