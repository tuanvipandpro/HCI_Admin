package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The gender. */
	private boolean gender;
	
	/** The address. */
	private String address;
	
	/** The phone. */
	private String phone;
	
	/** The store id. */
	private int storeId;
	
	/** The bank number. */
	private String bankNumber;
	
	/** The email. */
	private String email;
	
	/** The salary. */
	private float salary;
	
	/** The active. */
	private boolean active;
}
