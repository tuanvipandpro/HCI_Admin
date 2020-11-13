package tuanlm.hr.app.models.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftWorkRequest {
	
	/** The work id. */
	@NotNull
	private int workId;
	
	/** The shift employee id. */
	@NotNull
	private int shiftEmployeeId;
}
