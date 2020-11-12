package tuanlm.hr.app.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryShiftMapper {
	
	/**
	 * Store the history shift work.
	 *
	 * @param 
	 * @return the int
	 */
	@Insert("INSERT INTO WORK_HISTORY(work_id, emp_from, emp_to, datetime) "
			+ "VALUES( "
			+ "#{work_id}, "
			+ "#{emp_from}, "
			+ "#{emp_to}, "
			+ "#{datetime})")
	int storeHistoryShiftWork(int work_id, int emp_from, int emp_to, LocalDateTime datetime);
}
