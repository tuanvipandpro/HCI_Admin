package tuanlm.hr.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.models.model.Employee;
import tuanlm.hr.app.service.EmployeeService;

/**
 * The Class EmployeeController.
 */
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
	
	/** The service. */
	private EmployeeService service;
	
	/**
	 * Gets the employee by id.
	 *
	 * @param id the id
	 * @return the employee by id
	 */
	@Operation(summary = "Lấy thông tin nhân viên theo id", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable @Parameter(description = "Id của nhân viên (employeeId)") int id) {
		return new ResponseEntity<Employee>(service.getEmployeeById(id), HttpStatus.OK);
	}
	
	/**
	 * Gets the all employee.
	 *
	 * @return the all employee
	 */
	@Operation(summary = "Lấy thông tin tất cả nhân viên theo. Mode (0: All, 1: Active)", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-all/{mode}")
	public ResponseEntity<List<Employee>> getAllEmployee(@PathVariable int mode) {
		return new ResponseEntity<List<Employee>>(service.getAllEmployee(mode), HttpStatus.OK);
	}
	
	/**
	 * Gets the employee by store.
	 *
	 * @param storeId the store id
	 * @return the employee by store
	 */
	@Operation(summary = "Lấy thông tin tất cả nhân viên theo. Mode (0: All, 1: Active)", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/get-by-store/{storeId}")	
	public ResponseEntity<List<Employee>> getEmployeeByStore(@PathVariable int storeId) {
		return new ResponseEntity<List<Employee>>(service.getByStore(storeId), HttpStatus.OK);
	}
}
