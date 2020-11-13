package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkRequest {
	
	/** The id. */
	private int id;
	
	/** The employee id. */
	private int employeeId;
	
	/** The employee nm. */
	private String employeeNm;
	
	/** The shift id. */
	private int shiftId;
	
	/** The shift nm. */
	private String shiftNm;
	
	/** The start. */
	private String start;
	
	/** The end. */
	private String end;
	
	/** The is accept. */
	private boolean isAccept;
	
	/** The store id. */
	private int storeId;
}
