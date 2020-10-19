package tuanlm.hr.app.service;

import java.util.List;

import tuanlm.hr.app.models.model.Account;
import tuanlm.hr.app.models.request.InsertAccountRequest;

public interface AccountsService {
	
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
}
