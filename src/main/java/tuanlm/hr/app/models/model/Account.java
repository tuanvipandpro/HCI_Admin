package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The employee id. */
	private int employeeId;
	
	/** The role. */
	private String role;
	
	/** The active. */
	private boolean active;
}
