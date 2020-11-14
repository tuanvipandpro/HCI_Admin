package tuanlm.hr.app.service;

import tuanlm.hr.app.models.request.CheckAttendanceRequest;

/**
 * The Interface AttendanceService.
 */
public interface AttendanceService {
	
	/**
	 * Check attendance.
	 *
	 * @param request the request
	 */
	void checkAttendance(CheckAttendanceRequest request);
}
