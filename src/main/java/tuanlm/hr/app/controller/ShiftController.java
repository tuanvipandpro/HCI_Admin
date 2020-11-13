package tuanlm.hr.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.model.Shift;
import tuanlm.hr.app.service.ShiftService;

@AllArgsConstructor
@RestController
@RequestMapping("api/shift")
public class ShiftController {
	
	/** The service. */
	private ShiftService service;
	
	/**
	 * Gets the all shift.
	 *
	 * @return the all shift
	 */
	@Operation(summary = "Lấy thông tin các ca làm theo cửa hàng", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-all/{storeId}")
	public ResponseEntity<List<Shift>> getAllShiftByStoreId(@PathVariable int storeId) {
		return new ResponseEntity<List<Shift>>(service.getAllByStoreId(storeId) ,HttpStatus.OK);
	}
}
