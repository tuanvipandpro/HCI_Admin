package tuanlm.hr.app.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAttendanceRequest {
	
	/** The employee id. */
	@NotNull
	private int employeeId;
	
	/** The store id. */
	@NotNull
	private int storeId;
	
	/** The datetime. */
	@NotBlank
	private String datetime;
}
