package tuanlm.hr.app.service;

import java.util.List;

import tuanlm.hr.app.models.model.Account;
import tuanlm.hr.app.models.request.InsertAccountRequest;
import tuanlm.hr.app.models.response.LoginResponse;

/**
 * The Interface AccountsService.
 */
public interface AccountsService {
	
	/**
	 * Gets the account by email.
	 *
	 * @param email the email
	 * @return the account by email
	 */
	LoginResponse loginByEmail(String email);
	
	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	List<Account> getAccounts();
	
	/**
	 * Insert account.
	 *
	 * @param request the request
	 */
	void insertAccount(InsertAccountRequest request);
	
	
	/**
	 * Update active account.
	 *
	 * @param username the username
	 * @param mode the mode
	 */
	void updateActiveAccount(String username, int mode);
	
	
	/**
	 * Change password.
	 *
	 * @param username the username
	 * @param password the password
	 */
	void changePassword(String username, String password);
}
