package tuanlm.hr.app.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertAccountRequest {
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The employee id. */
	private int employeeId;
}
