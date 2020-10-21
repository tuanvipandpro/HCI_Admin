package tuanlm.hr.app.models.model;

import java.time.LocalDateTime;

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
	private LocalDateTime start;
	
	/** The end. */
	private LocalDateTime end;
}
