package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class WorkManagement.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkManagement {
	
	/** The id. */
	private int id;
	
	/** The shift nm. */
	private String shiftNm;
	
	/** The active. */
	private boolean active;
	
	/** The present. */
	private boolean present;
	
	/** The start. */
	private String start;
	
	/** The end. */
	private String end;
	
	/** The store id. */
	private int storeId;
	
	/** The store nm. */
	private String storeNm;
	
	/** The employee id. */
	private int employeeId;
	
	/** The employee nm. */
	private String employeeNm;
}
