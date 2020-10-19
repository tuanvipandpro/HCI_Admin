package tuanlm.hr.app.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	/** The employee id. */
	private int employeeId;
	
	/** The token. */
	private String token;
	
	/** The is admin. */
	private boolean isAdmin;
}
