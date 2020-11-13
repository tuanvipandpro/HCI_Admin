package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The start. */
	private String start;
	
	/** The end. */
	private String end;
	
	/** The max. */
	private int max;
	
	/** The active. */
	private boolean active;
	
	/** The store id. */
	private int storeId;
	
	/** The store nm. */
	private String storeNm;
}
