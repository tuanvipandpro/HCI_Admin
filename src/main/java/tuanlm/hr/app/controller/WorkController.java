package tuanlm.hr.app.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.model.Work;
import tuanlm.hr.app.models.model.WorkManagement;
import tuanlm.hr.app.models.model.WorkStore;
import tuanlm.hr.app.models.request.AssignWorkRequest;
import tuanlm.hr.app.models.request.ShiftWorkRequest;
import tuanlm.hr.app.models.response.TotalWorkReponse;
import tuanlm.hr.app.service.WorkService;

/**
 * The Class WorkController.
 */
@RestController
@RequestMapping("/api/work")
@AllArgsConstructor
public class WorkController {
	
	/** The service. */
	private WorkService service;
	

	/**
	 * Gets the work next.
	 *
	 * @param employeeId the employee id
	 * @return the work next
	 */
	@Operation(description = "Lấy danh sách ca làm tiếp theo của nhân viên", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-next-work/{employeeId}")
	public ResponseEntity<List<Work>> getWorkNext(@PathVariable int employeeId) {
		return new ResponseEntity<List<Work>>(service.getListWorkNextAvailable(employeeId), HttpStatus.OK);
	}
	

	/**
	 * Gets the total work by date.
	 *
	 * @param employeeId the employee id
	 * @param from the from
	 * @param to the to
	 * @return the total work by date
	 */
	@Operation(description = "Tổng hợp giờ công của nhân viên theo khoảng thời gian truyền vào", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-total-work/{employeeId}")
	public ResponseEntity<TotalWorkReponse> getTotalWorkByDate(
			@PathVariable int employeeId, 
			@NotBlank @Parameter(description = "Truyền theo format (yyyy-MM-dd)") String from, 
			@NotBlank @Parameter(description = "Truyền theo format (yyyy-MM-dd)") String to) {
		return new ResponseEntity<TotalWorkReponse>(service.getTotalWorkByDate(employeeId, from, to) , HttpStatus.OK);
	}
	
	/**
	 * Gets the work by id.
	 *
	 * @param id the id
	 * @return the work by id
	 */
	@Operation(description = "Lấy thông tin ca làm theo id", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-work-by-id/{employeeId}")
	public ResponseEntity<Work> getWorkById(@PathVariable int employeeId) {
		return new ResponseEntity<Work>(service.getWorkById(employeeId), HttpStatus.OK);
	}
	
	/**
	 * Gets the work store.
	 *
	 * @param employeeId the employee id
	 * @return the work store
	 */
	@Operation(description = "Lấy 10 ca làm gần nhất của nhân viên", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-work-by-employeeId/{employeeId}")	
	public ResponseEntity<List<WorkStore>> getWorkStore(@PathVariable int employeeId) {
		return new ResponseEntity<List<WorkStore>>(service.getWorkEmployee(employeeId), HttpStatus.OK);
	}
	
	/**
	 * Shift work.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(description = "Thực hiện chuyển ca", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/shift-work")
	public ResponseEntity<Void> shiftWork(@RequestBody @Valid ShiftWorkRequest request) {
		service.shiftWork(request.getWorkId(), request.getShiftEmployeeId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Gets the management works.
	 *
	 * @return the management works
	 */
	@Operation(description = "Lấy thông tin tất cả ca làm cho việc quản lý", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-work-management")
	public ResponseEntity<List<WorkManagement>> getManagementWorks() {
		return new ResponseEntity<List<WorkManagement>>(service.getWorkManagement(), HttpStatus.OK);
	}
	
	/**
	 * Delete work by id.
	 *
	 * @param workId the work id
	 * @return the response entity
	 */
	@Operation(description = "Update trạng thái ca làm theo id", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/active-work/{workId}/{mode}")
	public ResponseEntity<Void> deleteWorkById(@PathVariable int workId, @PathVariable int mode) {
		service.updateActiveWork(workId, mode);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Assign work.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(description = "Giao ca làm việc cho nhân viên", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/assign-work")
	public ResponseEntity<Boolean> assignWork(@RequestBody @Valid AssignWorkRequest request) {
		return (service.assginWork(request)) ?  new ResponseEntity<Boolean>(true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
	}
}
