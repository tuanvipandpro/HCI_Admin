package tuanlm.hr.app.service;

import java.time.LocalDateTime;
import java.util.List;

import tuanlm.hr.app.models.model.WorkRequest;

/**
 * The Interface WorkRequestService.
 */
public interface WorkRequestService {
	
	/**
	 * Gets the all work request.
	 *
	 * @param now the now
	 * @return the all work request
	 */
	List<WorkRequest> getAllWorkRequest(LocalDateTime now);
	
	
	/**
	 * Accept work request.
	 *
	 * @param id the id
	 * @param request the request
	 */
	void acceptWorkRequest(int id, WorkRequest request);
}
