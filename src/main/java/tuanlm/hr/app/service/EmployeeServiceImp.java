package tuanlm.hr.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.EmployeeMapper;
import tuanlm.hr.app.models.model.Employee;


/**
 * The Class EmployeeServiceImp.
 */
@Service

@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService{
	
	/** The mapper. */
	private EmployeeMapper mapper;

	/**
	 * Gets the employee by id.
	 *
	 * @param id the id
	 * @return the employee by id
	 */
	@Override
	public Employee getEmployeeById(int id) {
		return mapper.getEmployeeById(id);
	}

	/**
	 * Gets the all employee.
	 *
	 * @param mode the mode
	 * @return the all employee
	 */
	@Override
	public List<Employee> getAllEmployee(int mode) {
		return mapper.getAllEmployee(mode);
	}
}
