package tuanlm.hr.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkRequest {
	private int id;
	private int employeeId;
	private String employeeNm;
	private int shiftId;
	private String shiftNm;
	private String start;
	private String end;
	private boolean isAccept;
	private int storeId;
}
