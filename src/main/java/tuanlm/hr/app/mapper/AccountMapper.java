package tuanlm.hr.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tuanlm.hr.app.models.model.Account;

@Mapper
public interface AccountMapper {
	
	/**
	 * Gets the account by username.
	 *
	 * @param username the username
	 * @return the account by username
	 */
	@Select(""
			+ "SELECT "
			+ "		A.Username 			AS username, "
			+ "		A.Password 			AS password, "
			+ "		A.\"employeeId\" 	AS employeeId, "
			+ "		R.role 				AS role, "
			+ "		A.active			AS active "
			+ "FROM "
			+ "		account AS A "
			+ "INNER JOIN "
			+ "		role AS R "
			+ "ON "
			+ "		A.\"roleId\" = R.Id "
			+ "WHERE "
			+ "		A.username = #{username} "
			+ "		AND A.active = true ")
	Account getAccountByUsername(String username);
	
	/**
	 * Gets the all account.
	 *
	 * @return the all account
	 */
	@Select(""
			+ "SELECT "
			+ "		A.Username 			AS username, "
			+ "		A.Password 			AS password, "
			+ "		A.\"employeeId\" 	AS employeeId, "
			+ "		R.role 				AS role, "
			+ "		A.active			AS active "
			+ "FROM "
			+ "		account AS A "
			+ "INNER JOIN "
			+ "		role AS R "
			+ "ON "
			+ "		A.\"roleId\" = R.Id "
			+ "ORDER BY "
			+ "		A.Username ")	
	List<Account> getAllAccount();
	
	/**
	 * Insert account.
	 *
	 * @param username the username
	 * @param password the password
	 * @param employeeId the employee id
	 * @param roleId the role id
	 * @param active the active
	 */
	@Insert(""
			+ "INSERT INTO account(username, password, \"employeeId\", \"roleId\", active) "
			+ "VALUES(#{username}, #{password}, #{employeeId}, #{roleId}, #{active}) "
			+ "")
	void insertAccount(String username, String password, int employeeId, int roleId, boolean active);
	
	/**
	 * Update active account.
	 *
	 * @param username the username
	 * @param mode the mode
	 */
	@Update("<script> "
			+ "UPDATE "
			+ "		account "
			+ "<if test=\"mode == 0\"> "
			+ "		SET active = false "
			+ "</if> "
			+ "<if test=\"mode == 1\"> "
			+ "		SET active = true "
			+ "</if> "			
			+ "WHERE "
			+ "		username = #{username} "
			+ "</script>")
	void updateActiveAccount(String username, int mode);
	
	/**
	 * Change password account.
	 *
	 * @param username the username
	 * @param password the password
	 */
	@Update(""
			+ "UPDATE "
			+ "		account "
			+ "SET "
			+ "		password = #{password} "		
			+ "WHERE "
			+ "		username = #{username} "
			+ "")
	void changePassword(String username, String password);
}
