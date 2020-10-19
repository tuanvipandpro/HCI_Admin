package tuanlm.hr.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.model.Account;
import tuanlm.hr.app.models.request.InsertAccountRequest;
import tuanlm.hr.app.service.AccountsService;

@RestController
@RequestMapping("api/account")
@AllArgsConstructor
public class AccountController {
	
	/** The service. */
	private AccountsService service;
	
	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	@GetMapping("/get-all-account")
	@Operation(description = "Lấy thông tin tất cả tài khoản trong hệ thống", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<Account>> getAccount() {
		return new ResponseEntity<List<Account>>(service.getAccounts(), HttpStatus.OK);
	}
	
	/**
	 * Upsert account.
	 *
	 * @param request the request
	 */
	@PostMapping("/insert-account")
	@Operation(description = "Insert tài khoản trong hệ thống", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Void> upsertAccount(@RequestBody InsertAccountRequest request) {
		service.insertAccount(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Update active account.
	 *
	 * @param username the username
	 * @param mode the mode
	 * @return the response entity
	 */
	@PutMapping("/update-active-account/{username}/{mode}")
	@Operation(description = "Update trạng thái của account (0: Xóa, 1: Active)", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Void> updateActiveAccount(@PathVariable String username, @PathVariable int mode) {
		service.updateActiveAccount(username, mode);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
