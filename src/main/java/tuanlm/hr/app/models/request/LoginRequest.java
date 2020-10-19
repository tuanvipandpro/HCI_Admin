package tuanlm.hr.app.models.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	/** The username. */
	@NotBlank
	private String username;
	
	/** The password. */
	@NotBlank
	private String password;
}
