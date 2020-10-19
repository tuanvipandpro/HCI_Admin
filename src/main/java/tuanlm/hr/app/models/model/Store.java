package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The address. */
	private String address;
	
	/** The phone. */
	private String phone;
	
	/** The email. */
	private String email;
	
	/** The fax. */
	private String fax;
	
	/** The active. */
	private boolean active;
}
