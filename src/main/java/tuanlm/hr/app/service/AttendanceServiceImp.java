package tuanlm.hr.app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.AttendanceMapper;
import tuanlm.hr.app.mapper.WorkMapper;
import tuanlm.hr.app.models.model.Work;
import tuanlm.hr.app.models.request.CheckAttendanceRequest;

/**
 * The Class AttendanceServiceImp.
 */
@Service
@AllArgsConstructor
public class AttendanceServiceImp implements AttendanceService {
	
	/** The attendance mapper. */
	private AttendanceMapper attendanceMapper;
	
	/** The work mapper. */
	private WorkMapper workMapper;

	/**
	 * Check attendance.
	 *
	 * @param request the request
	 */
	@Override
	public void checkAttendance(CheckAttendanceRequest request) {
		attendanceMapper.checkAttendance(request.getEmployeeId(), request.getStoreId(), LocalDateTime.parse(request.getDatetime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		Work work = workMapper.getWorkNow(request.getEmployeeId(), request.getStoreId(), LocalDateTime.parse(request.getDatetime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		if (work != null && !work.isPresent()) {
			workMapper.presentWork(work.getId());
		}
	}
}
