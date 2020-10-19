package tuanlm.hr.app.service;

import java.util.List;

import tuanlm.hr.app.models.model.Employee;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmployeeService.
 */
public interface EmployeeService {
	
	/**
	 * Gets the employee by id.
	 *
	 * @param id the id
	 * @return the employee by id
	 */
	Employee getEmployeeById(int id);
	
	
	/**
	 * Gets the all employee.
	 *
	 * @return the all employee
	 */
	List<Employee> getAllEmployee(int mode);
}
