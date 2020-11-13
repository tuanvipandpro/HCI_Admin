package tuanlm.hr.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.ShiftMapper;
import tuanlm.hr.app.mapper.WorkMapper;
import tuanlm.hr.app.mapper.WorkRequestMapper;
import tuanlm.hr.app.models.model.Shift;
import tuanlm.hr.app.models.model.WorkAvailable;
import tuanlm.hr.app.models.model.WorkRequest;
import tuanlm.hr.app.models.model.WorkRequestShort;
import tuanlm.hr.app.models.model.WorkStore;
import tuanlm.hr.app.models.request.RegisterWorkRequest;

@Service
@AllArgsConstructor
public class WorkRequestServiceImp implements WorkRequestService {
	
	/** The mapper. */
	private WorkRequestMapper mapper;
	
	/** The work mapper. */
	private WorkMapper workMapper;
	
	/** The shift mapper. */
	private ShiftMapper shiftMapper;

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
	public void registerWorkRequest(RegisterWorkRequest request) {
		mapper.createWorkRequest(request.getEmployeeId(), request.getShiftId(), LocalDate.parse(request.getDate()), null, request.getStoreId());
	}

	/**
	 * Gets the work available by store and date.
	 *
	 * @param employeeId the employee id
	 * @param storeId the store id
	 * @param date the date
	 * @return the work available by store and date
	 */
	@Override
	public List<WorkAvailable> getWorkAvailableByStoreAndDate(int employeeId, int storeId, String date) {
		List<Shift> shifts = shiftMapper.getShiftListByStoreId(storeId);
		LocalDate dateParse = LocalDate.parse(date);
		List<WorkAvailable> responseList = new ArrayList<WorkAvailable>();
		
		if (shifts == null) return null;
		
		List<WorkStore> workStores = workMapper.getWorkByDate(
				employeeId, 
				LocalDate.parse(date).atStartOfDay(), 
				LocalDate.parse(date).atStartOfDay().plusDays(1));
		
		if (workStores != null && workStores.size() > 0) {			
			responseList =  filterWorkAvailableList(shifts, workStores, dateParse);
		} 
		else {
			for (Shift s : shifts) {
				responseList.add(new WorkAvailable(
						s.getStoreId(), 
						s.getStoreNm(), 
						s.getId(), 
						s.getName(), 
						workMapper.countWorkByShiftAndDate(s.getId(), dateParse, dateParse.plusDays(1)) >= s.getMax()));
			}
		}
		
		List<WorkRequestShort> list = mapper.getWorkRequestEmployeeByStoreAndDate(employeeId, storeId, dateParse);
		
		// Check if employee is requested this shift
		if (list == null) return responseList;
		else {
			List<Integer> workRQShortShiftId = list.stream().map(w -> w.getShiftId()).collect(Collectors.toList());
			return responseList.stream().filter(w -> !workRQShortShiftId.contains(w.getShiftId())).collect(Collectors.toList());
		}
	}
	
	/**
	 * Filter work available list.
	 *
	 * @param shifts the shifts
	 * @param workStores the work stores
	 * @return the list
	 */
	private List<WorkAvailable> filterWorkAvailableList(
			List<Shift> shifts, 
			List<WorkStore> workStores, 
			LocalDate date) {
		
		List<WorkAvailable> responseList = new ArrayList<WorkAvailable>();
		
		List<Integer> shiftIdList = shifts.stream().map(shift -> shift.getId()).collect(Collectors.toList());
		
		// Check If employee has work at this shift
		workStores.forEach(workStore -> {
			if (shiftIdList.contains(workStore.getShiftId())) {
				
				Shift shift = null;
				
				for (Shift s : shifts) {
					if (s.getId() == workStore.getShiftId()) shift = s;
				}
				
				responseList.add(new WorkAvailable(
						workStore.getStoreId(), 
						workStore.getStoreNm(), 
						workStore.getShiftId(), 
						shift.getName(), 
						workMapper.countWorkByShiftAndDate(
								workStore.getShiftId(), 
								date, 
								date.plusDays(1)) >= shift.getMax()));
			}
		});
		
		return responseList;
	}
}
