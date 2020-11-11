package tuanlm.hr.app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.WorkMapper;
import tuanlm.hr.app.mapper.WorkRequestMapper;
import tuanlm.hr.app.models.model.WorkRequest;
import tuanlm.hr.app.models.request.AssignWorkRequest;

@Service
@AllArgsConstructor
public class WorkRequestServiceImp implements WorkRequestService {
	
	/** The mapper. */
	private WorkRequestMapper mapper;
	
	/** The work mapper. */
	private WorkMapper workMapper;

	/**
	 * Gets the all work request.
	 *
	 * @return the all work request
	 */
	@Override
	public List<WorkRequest> getAllWorkRequest(LocalDateTime now) {
		return mapper.getAllWorkRequest(now);
	}

	/**
	 * Accept work request.
	 *
	 * @param id the id
	 * @param request the request
	 */
	@Override
	public void acceptWorkRequest(int id, WorkRequest request) {
		mapper.acceptWorkRequest(id);
		workMapper.createWork(
				request.getEmployeeId(), 
				request.getShiftId(), 
				LocalDateTime.parse(request.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
				LocalDateTime.parse(request.getEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 
				request.getStoreId());
	}

	/**
	 * Register work request.
	 *
	 * @param request the request
	 */
	@Override
	public void registerWorkRequest(AssignWorkRequest request) {
		// TODO Auto-generated method stub
	}
}
