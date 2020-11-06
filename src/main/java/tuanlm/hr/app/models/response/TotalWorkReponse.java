package tuanlm.hr.app.models.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuanlm.hr.app.models.model.WorkStore;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalWorkReponse {
	private String totalTime;
	private List<WorkStore> worksList;
}
