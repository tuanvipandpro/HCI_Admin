package tuanlm.hr.app.models.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkRequestShort {
	
	/** The id. */
	private int id;
	
	/** The employee id. */
	private int employeeId;
	
	/** The shift id. */
	private int shiftId;
	
	/** The store id. */
	private int storeId;
	
	/** The date. */
	private LocalDate date;
	
	/** The is accept. */
	private boolean isAccept;
	
	/** The update person. */
	private int updatePerson;
}
