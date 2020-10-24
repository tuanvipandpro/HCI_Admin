package tuanlm.hr.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tuanlm.hr.app.models.model.Employee;

/**
 * The Interface EmployeeMapper.
 */
@Mapper
public interface EmployeeMapper {
	
	/**
	 * Gets the employee by id.
	 *
	 * @param id the id
	 * @return the employee by id
	 */
	@Select(""
			+ "SELECT "
			+ "		E.id 					AS id, "
			+ "		E.name					AS name, "
			+ "		E.address				AS address, "
			+ "		E.gender				AS gender, "
			+ "		E.phone					AS phone, "
			+ "		E.\"storeId\"			AS storeId, "
			+ "		E.\"bankNumber\"		AS bankNumber, "
			+ "		E.email					AS email, "
			+ "		E.salary				AS salary "
			+ "FROM "
			+ "		Employee AS E "
			+ "WHERE "
			+ "		E.Active = true "
			+ "		AND	E.id = #{id}")
	Employee getEmployeeById(int id);
	
	/**
	 * Gets the all employee.
	 *
	 * @param mode the mode
	 * @return the all employee
	 */
	@Select("<script> "
			+ "SELECT "
			+ "		E.id 					AS id, "
			+ "		E.name					AS name, "
			+ "		E.address				AS address, "
			+ "		E.gender				AS gender, "
			+ "		E.phone					AS phone, "
			+ "		E.\"storeId\"			AS storeId, "
			+ "		E.\"bankNumber\"		AS bankNumber, "
			+ "		E.email					AS email, "
			+ "		E.salary				AS salary, "
			+ "		E.active				AS active "
			+ "FROM "
			+ "		Employee AS E "
			+ "<if test=\"mode == 1\"> "
			+ "WHERE "
			+ "		E.active = true "
			+ "</if> "
			+ "ORDER BY "
			+ "		E.id "
			+ "</script> ")
	List<Employee> getAllEmployee(int mode);
	
	/**
	 * Gets the employee by store id.
	 *
	 * @param storeId the store id
	 * @return the employee by store id
	 */
	@Select(""
			+ "SELECT "
			+ "		E.id 					AS id, "
			+ "		E.name					AS name, "
			+ "		E.address				AS address, "
			+ "		E.gender				AS gender, "
			+ "		E.phone					AS phone, "
			+ "		E.\"storeId\"			AS storeId, "
			+ "		E.\"bankNumber\"		AS bankNumber, "
			+ "		E.email					AS email, "
			+ "		E.salary				AS salary, "
			+ "		E.active				AS active "
			+ "FROM "
			+ "		Employee AS E "
			+ "WHERE "
			+ "		E.active = true "
			+ "		AND E.\"storeId\" = #{storeId} "
			+ "ORDER BY "
			+ "		E.id "
			+ "")	
	List<Employee> getEmployeeByStoreId(int storeId);
	
	/**
	 * Adds the new employee.
	 *
	 * @param employeeDataset the employee dataset
	 * @return the int
	 */
	@Insert("Insert into "
			+ "Employee(name, gender, address, phone, \"storeId\", \"bankNumber\", email, active, salary) "
			+ "Values( "
			+ "#{name}, "
			+ "#{gender}, "
			+ "#{address}, "
			+ "#{phone}, "
			+ "#{storeId}, "
			+ "#{bankNumber}, "
			+ "#{email}, "
			+ "#{active}, "
			+ "#{salary})")
	int addNewEmployee(String name, boolean gender, String address, String phone, int storeId, String bankNumber, String email, boolean active, float salary);

}
