package tuanlm.hr.app.mapper;

import java.util.List;

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
}
