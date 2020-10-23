package tuanlm.hr.app.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.WorkMapper;
import tuanlm.hr.app.models.model.Work;
import tuanlm.hr.app.models.model.WorkManagement;
import tuanlm.hr.app.models.model.WorkStore;
import tuanlm.hr.app.models.request.AssignWorkRequest;
import tuanlm.hr.app.models.response.TotalWorkReponse;
import tuanlm.hr.app.utils.DateTimeUtils;

/**
 * The Class WorkServiceImp.
 */
@Service
@AllArgsConstructor
public class WorkServiceImp implements WorkService {
	
	/** The mapper. */
	private WorkMapper mapper;
	
	/**
	 * Gets the list work next available.
	 *
	 * @return the list work next available
	 */
	public List<Work> getListWorkNextAvailable(int employeeId) {
		return mapper.getNextWork(employeeId, LocalDateTime.now());
	}

	/**
	 * Gets the total work by date.
	 *
	 * @param employeeId the employee id
	 * @param from the from
	 * @param to the to
	 * @return the total work by date
	 */
	@Override
	public TotalWorkReponse getTotalWorkByDate(int employeeId, String from, String to) {
		Duration totalTime = Duration.ZERO;
		List<Work> works = mapper.getTotalWorkByDate(employeeId, LocalDate.parse(from).atStartOfDay(), LocalDate.parse(to).atStartOfDay().plusDays(1));
		
		for (Work work : works) {
			if (work.isPresent()) {
				totalTime = totalTime.plus(Duration.between(work.getStart(), work.getEnd()));
			}
		}
		return new TotalWorkReponse(DateTimeUtils.formatDurationToTimeString(totalTime), works);
	}
	
	/**
	 * Shift work.
	 *
	 * @param workId the work id
	 * @param shiftEmployeeId the shift employee id
	 */
	@Override
	public void shiftWork(int workId, int shiftEmployeeId) {
		mapper.shiftWork(workId, shiftEmployeeId);
	}

	/**
	 * Gets the work management.
	 *
	 * @return the work management
	 */
	@Override
	public List<WorkManagement> getWorkManagement() {
		return mapper.getWorkMaganement();
	}

	/**
	 * Delete work.
	 *
	 * @param workId the work id
	 */
	@Override
	public void updateActiveWork(int workId, int mode) {
		mapper.updateStatusWork(mode, workId);
	}

	/**
	 * Gets the work by id.
	 *
	 * @param id the id
	 * @return the work by id
	 */
	@Override
	public Work getWorkById(int id) {
		return mapper.getWorkById(id);
	}

	/**
	 * Assgin work.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	@Override
	public boolean assginWork(AssignWorkRequest request) {
		LocalDateTime start = LocalDateTime.parse(request.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime end = LocalDateTime.parse(request.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		Work work = mapper.checkExistWork(
				request.getStoreId(), 
				request.getEmployeeId(), 
				request.getShiftId(), 
				start, 
				end);
		
		if (work != null) {
			return false;
		}
		
		mapper.createWork(request.getEmployeeId(), request.getShiftId(), start, end, request.getStoreId());
		return true;
	}

	/**
	 * Gets the work employee.
	 *
	 * @param employeeId the employee id
	 * @return the work employee
	 */
	@Override
	public List<WorkStore> getWorkEmployee(int employeeId) {
		return mapper.getWorkByEmployee(employeeId, LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusHours(4));
	}

	/**
	 * Gets the work by date.
	 *
	 * @param employeeId the employee id
	 * @param date the date
	 * @return the work by date
	 */
	@Override
	public List<WorkStore> getWorkByDate(int employeeId, String date) {
		return mapper.getWorkByDate(employeeId, 
				LocalDate.parse(date).atStartOfDay(),
				LocalDate.parse(date).atStartOfDay().plusDays(1));
	}
}
