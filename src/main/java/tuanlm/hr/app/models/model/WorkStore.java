package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkStore {
	
	/** The id. */
	private int id;
	
	/** The employee id. */
	private int employeeId;
	
	/** The store id. */
	private int storeId;
	
	/** The store nm. */
	private String storeNm;
	
	/** The present. */
	private boolean present;
	
	/** The shift id. */
	private int shiftId;
	
	/** The status id. */
	private int statusId;
	
	/** The note. */
	private String note;
	
	/** The start. */
	private String start;
	
	/** The end. */
	private String end;
	
	/** The present time. */
	private String presentTime;
}
