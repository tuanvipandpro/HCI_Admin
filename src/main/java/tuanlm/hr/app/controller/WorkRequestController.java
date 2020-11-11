package tuanlm.hr.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.model.WorkRequest;
import tuanlm.hr.app.models.request.AssignWorkRequest;
import tuanlm.hr.app.service.WorkRequestService;

@RestController
@AllArgsConstructor
@RequestMapping("api/work-request")
public class WorkRequestController {
	
	/** The service. */
	private WorkRequestService service;
	
	/**
	 * Gets the all work request.
	 *
	 * @param now the now
	 * @return the all work request
	 */
	@Operation(summary = "Lấy tất cả các ca làm được yêu cầu ", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-all")
	public ResponseEntity<List<WorkRequest>> getAllWorkRequest(
			@RequestParam @Parameter(description = "Truyền về ngày giờ hiện tại. Format (yyyy-MM-ddTHH:mm:ss)") String now) {
		return new ResponseEntity<List<WorkRequest>>(service.getAllWorkRequest(LocalDateTime.parse(now)), HttpStatus.OK);
	}
	
	/**
	 * Accept work request.
	 *
	 * @param id the id
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(summary = "Duyệt ca làm việc ", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/accept-work-request/{id}")
	public ResponseEntity<Void> acceptWorkRequest(@PathVariable int id, @RequestBody WorkRequest request) {
		service.acceptWorkRequest(id, request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Request work request.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(summary = "Đăng ký ca làm (chưa xong, gọi ngu t chửi chết mẹ tụi m) ", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/create-work-request")	
	public ResponseEntity<Void> requestWorkRequest(@RequestBody AssignWorkRequest request) {
		service.registerWorkRequest(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
