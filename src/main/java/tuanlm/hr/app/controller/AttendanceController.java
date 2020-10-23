package tuanlm.hr.app.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.request.CheckAttendanceRequest;
import tuanlm.hr.app.service.AttendanceService;

/**
 * The Class AttendanceController.
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/attendance")
public class AttendanceController {
	
	/** The service. */
	private AttendanceService service;
	
	/**
	 * Check attendance for employee.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(summary = "Điểm danh nhân viên (In Process)", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("check-attendance")
	public ResponseEntity<Void> checkAttendanceForEmployee(@RequestBody @Valid CheckAttendanceRequest request) {
		service.checkAttendance(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
