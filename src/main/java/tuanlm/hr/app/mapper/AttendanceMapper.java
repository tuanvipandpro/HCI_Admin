package tuanlm.hr.app.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AttendanceMapper {
	
	@Insert(""
			+ "INSERT INTO attendance (\"employeeId\", \"storeId\", datetime, active) "
			+ "VALUES (#{employeeId}, #{storeId}, #{datetime}, true) ")
	void checkAttendance(int employeeId, int storeId, LocalDateTime datetime);
}
