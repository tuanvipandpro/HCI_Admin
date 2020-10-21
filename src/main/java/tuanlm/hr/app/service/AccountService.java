package tuanlm.hr.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tuanlm.hr.app.mapper.AccountMapper;
import tuanlm.hr.app.models.model.Account;
import tuanlm.hr.app.models.request.InsertAccountRequest;
import tuanlm.hr.app.models.response.LoginResponse;
import tuanlm.hr.app.utils.AppConstants;
import tuanlm.hr.app.utils.JwtUtils;

/**
 * The Class AccountService.
 */
@Service
public class AccountService implements UserDetailsService, AccountsService  {
	
	/** The mapper. */
	private AccountMapper mapper;
	
	/** The encoder. */
	@Autowired
	private PasswordEncoder encoder;
	
	/**
	 * Instantiates a new account service.
	 *
	 * @param mapper the mapper
	 */
	public AccountService(AccountMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		Account account = Optional.of(mapper.getAccountByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException(username + " is not exist !"));
		
		return new User(
				account.getUsername(), 
				account.getPassword(), 
				Arrays.asList(new SimpleGrantedAuthority(account.getRole())));
	}
	
	/**
	 * Gets the login response.
	 *
	 * @param username the username
	 * @return the login response
	 */
	public LoginResponse getLoginResponse(String username, int mode) {
		Account account = Optional.of(mapper.getAccountByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException(username + " is not exist !"));
		
		if (account == null) {
			return null;
		}
		else if (mode == AppConstants.LOGIN_ADMIN){
			return (!account.getRole().equals("ROLE_ADMIN")) ? null : 
				new LoginResponse(account.getEmployeeId(), JwtUtils.getJwt(account.getUsername()), true);
		}
		else {
			return new LoginResponse(account.getEmployeeId(), JwtUtils.getJwt(account.getUsername()), false);
		}
	}

	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	@Override
	public List<Account> getAccounts() {
		return mapper.getAllAccount();
	}

	/**
	 * Upsert account.
	 *
	 * @param request the request
	 */
	@Override
	public void insertAccount(InsertAccountRequest request) {
		mapper.insertAccount(request.getUsername(), encoder.encode(request.getPassword()), request.getEmployeeId(), 1, true);
	}

	/**
	 * Update active account.
	 *
	 * @param username the username
	 * @param mode the mode
	 */
	@Override
	public void updateActiveAccount(String username, int mode) {
		mapper.updateActiveAccount(username, mode);
	}

	/**
	 * Change password.
	 *
	 * @param username the username
	 * @param password the password
	 */
	@Override
	public void changePassword(String username, String password) {
		mapper.changePassword(username, encoder.encode(password));
	}
}
