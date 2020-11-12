package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkAvailable {
	
	/** The store id. */
	private int storeId;
	
	/** The store nm. */
	private String storeNm;
	
	/** The shift id. */
	private int shiftId;
	
	/** The shift nm. */
	private String shiftNm;
	
	/** The is max. */
	private boolean isMax;
}
