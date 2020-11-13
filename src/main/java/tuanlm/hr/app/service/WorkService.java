package tuanlm.hr.app.service;

import java.time.LocalDateTime;
import java.util.List;

import tuanlm.hr.app.models.model.Work;
import tuanlm.hr.app.models.model.WorkManagement;
import tuanlm.hr.app.models.model.WorkStore;
import tuanlm.hr.app.models.request.AssignWorkRequest;
import tuanlm.hr.app.models.response.TotalWorkReponse;

/**
 * The Interface WorkService.
 */
public interface WorkService {
	
	/**
	 * Gets the work by id.
	 *
	 * @param id the id
	 * @return the work by id
	 */
	Work getWorkById(int id);

	/**
	 * Gets the list work next available.
	 *
	 * @param employeeId the employee id
	 * @return the list work next available
	 */
	List<Work> getListWorkNextAvailable(int employeeId);
	
	
	/**
	 * Gets the total work by date.
	 * 
	 * @param employeeId the employeeId
	 * @param from the from
	 * @param to the to
	 * @return the total work by date
	 */
	TotalWorkReponse getTotalWorkByDate(int employeeId, String from, String to);
	
	/**
	 * Gets the work management.
	 *
	 * @return the work management
	 */
	List<WorkManagement> getWorkManagement();
	
	/**
	 * Gets the work by date.
	 *
	 * @return the work by date
	 */
	List<WorkStore> getWorkByDate(int employeeId, String date);
	
	/**
	 * Gets the work store.
	 *
	 * @param employeeId the employee id
	 * @return the work store
	 */
	List<WorkStore> getWorkEmployee(int employeeId);
	
	/**
	 * Gets the public work.
	 *
	 * @param datetime the datetime
	 * @return the public work
	 */
	List<WorkStore> getPublicWork(LocalDateTime datetime);
	
	/**
	 * Public work.
	 *
	 * @param workId the work id
	 */
	void publicWork(int workId);
	
	/**
	 * Shift work.
	 *
	 * @param workId the work id
	 * @param shiftEmployeeId the shift employee id
	 */
	void shiftWork(int workId, int shiftEmployeeId);
	
	/**
	 * Delete work.
	 *
	 * @param workId the work id
	 */
	void updateActiveWork(int workId, int mode);
	
	/**
	 * Assgin work.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	boolean assginWork(AssignWorkRequest request);
}
