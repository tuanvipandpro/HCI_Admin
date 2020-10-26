package tuanlm.hr.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.EmployeeMapper;
import tuanlm.hr.app.models.model.Employee;
import tuanlm.hr.app.models.request.InsertEmployee;


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

	/**
	 * Gets the by store.
	 *
	 * @param storeId the store id
	 * @return the by store
	 */
	@Override
	public List<Employee> getByStore(int storeId) {
		return mapper.getEmployeeByStoreId(storeId);
	}
	
	/**
	 * Adds the new employee.
	 *
	 * @param employeeDataset the employee dataset
	 * @return the int
	 */
	@Override
	public int addNewEmployee(InsertEmployee employeeDataset) {
		return mapper.addNewEmployee(employeeDataset.getName(), employeeDataset.isGender(), employeeDataset.getAddress(), employeeDataset.getPhone(), employeeDataset.getStoreId(), employeeDataset.getBankNumber(), employeeDataset.getEmail(), employeeDataset.isActive(), employeeDataset.getSalary());
	}

	/**
	 * Update status employee
	 * @param employeeDataset the employee dataset
	 * @return the int
	 */
	@Override
	public int updateStatusEmployee(Employee employeeDataset) {
		return mapper.updateStatusEmployee(employeeDataset.getId(), employeeDataset.isActive());
	}
}
