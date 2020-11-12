package tuanlm.hr.app.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterWorkRequest {
	/** The store id. */
	@NotNull
	private int storeId;
	
	/** The shift id. */
	@NotNull
	private int shiftId;
	
	/** The employee id. */
	@NotNull
	private int employeeId;
	
	/** The date. */
	@NotBlank
	private String date;
}
