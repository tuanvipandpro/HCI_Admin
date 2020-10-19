package tuanlm.hr.app.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.request.LoginRequest;
import tuanlm.hr.app.models.response.LoginResponse;
import tuanlm.hr.app.service.AccountService;
import tuanlm.hr.app.utils.AppConstants;

/**
 * The Class LoginController.
 */
@RestController
@RequestMapping("/api/authenticate")

/**
 * Instantiates a new login controller.
 *
 * @param authenticationManager the authentication manager
 * @param accountService the account service
 */
@AllArgsConstructor
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
	@Operation(description = "Api Login User")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(
			@RequestBody @Valid LoginRequest request, 
			Device device) {
		
		SecurityContextHolder.getContext().setAuthentication(authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())));
		
		int mode = (device.isNormal()) ? AppConstants.LOGIN_ADMIN : AppConstants.LOGIN_MEMBER;
		
		LoginResponse response = accountService.getLoginResponse(request.getUsername(), mode);
		
		return (response == null) ? new ResponseEntity<LoginResponse>(HttpStatus.UNAUTHORIZED)  : new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}
}
