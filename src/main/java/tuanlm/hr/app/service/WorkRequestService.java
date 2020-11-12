package tuanlm.hr.app.service;

import java.time.LocalDateTime;
import java.util.List;

import tuanlm.hr.app.models.model.WorkAvailable;
import tuanlm.hr.app.models.model.WorkRequest;
import tuanlm.hr.app.models.request.RegisterWorkRequest;

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
	 * Gets the work available by store and date.
	 *
	 * @param employeeId the employee id
	 * @param storeId the store id
	 * @param date the date
	 * @return the work available by store and date
	 */
	List<WorkAvailable> getWorkAvailableByStoreAndDate(int employeeId, int storeId, String date);
	
	
	/**
	 * Accept work request.
	 *
	 * @param id the id
	 * @param request the request
	 */
	void acceptWorkRequest(int id, WorkRequest request);
	
	
	/**
	 * Register work request.
	 *
	 * @param request the request
	 */
	void registerWorkRequest(RegisterWorkRequest request);
}
