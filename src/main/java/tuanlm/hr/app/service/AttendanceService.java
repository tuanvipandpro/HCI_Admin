package tuanlm.hr.app.service;

import tuanlm.hr.app.models.request.CheckAttendanceRequest;

public interface AttendanceService {
	void checkAttendance(CheckAttendanceRequest request);
}
