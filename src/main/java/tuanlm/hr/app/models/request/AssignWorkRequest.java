package tuanlm.hr.app.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignWorkRequest {
	
	/** The store id. */
	@NotNull
	private int storeId;
	
	/** The shift id. */
	@NotNull
	private int shiftId;
	
	/** The employee id. */
	@NotNull
	private int employeeId;
	
	/** The start time. */
	@NotBlank
	private String startTime;
	
	/** The end time. */
	@NotBlank
	private String endTime;
}
