package tuanlm.hr.app.service;

import java.util.List;

import tuanlm.hr.app.models.model.Employee;
import tuanlm.hr.app.models.request.InsertEmployee;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmployeeService.
 */
/**
 * @author PC
 *
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
	 * @param mode the mode
	 * @return the all employee
	 */
	List<Employee> getAllEmployee(int mode);
	
	/**
	 * Gets the by store.
	 *
	 * @param storeId the store id
	 * @return the by store
	 */
	List<Employee> getByStore(int storeId);
	
	/**
	 * Adds the new employee.
	 *
	 * @param employee the employee
	 * @return the int
	 */
	int addNewEmployee(InsertEmployee employeeDataset);
	
	/**
	 * @param employeeDataset
	 * @return
	 */
	int updateStatusEmployee(Employee employeeDataset);
}
