package tuanlm.hr.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.model.Store;
import tuanlm.hr.app.service.StoreService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {
	
	/** The service. */
	private StoreService service;
	
	/**
	 * Gets the all store.
	 *
	 * @return the all store
	 */
	@Operation(description = "Lấy tất cả các cửa hàng đang hoạt động", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-all")
	public ResponseEntity<List<Store>> getAllStore() {
		return new ResponseEntity<List<Store>>(service.getAllStore(), HttpStatus.OK);
	}
}
