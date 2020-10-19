package tuanlm.hr.app.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tuanlm.hr.app.models.model.WorkRequest;

/**
 * The Interface WorkRequestMapper.
 */
@Mapper
public interface WorkRequestMapper {
	
	/**
	 * Gets the all work request.
	 *
	 * @param now the now
	 * @return the all work request
	 */
	@Select(""
			+ "SELECT "
			+ "		WR.id											AS id, "
			+ "		E.id											AS employeeId, "
			+ "		E.name											AS employeeNm, "
			+ "		S.id 											AS shiftId, "
			+ "		S.name	 										AS shiftNm, "
			+ "		CAST(WR.date AS varchar) || ' ' || S.start 		AS start, "
			+ "		CAST(WR.date AS varchar) || ' ' || S.\"end\" 	AS end, "
			+ "		WR.accept 										AS isAccept,"
			+ "		WR.store_id										AS storeId "
			+ "FROM "
			+ "		work_request AS WR, "
			+ "		shift AS S, "
			+ "		employee AS E "
			+ "WHERE "
			+ "		WR.\"shiftId\" = S.id "
			+ "		AND WR.\"employeeId\" = E.id "
			+ "		AND WR.accept = false "
			+ "		AND CAST((CAST(WR.date AS varchar) || ' ' || S.start) AS timestamp) >= #{now} "
			+ "ORDER BY "
			+ "		CAST((CAST(WR.date AS varchar) || ' ' || S.start) AS timestamp) "
			+ "")
	List<WorkRequest> getAllWorkRequest(LocalDateTime now);
	
	/**
	 * Accept work request.
	 *
	 * @param id the id
	 */
	@Update(""
			+ "UPDATE work_request "
			+ "SET accept = true "
			+ "WHERE id = #{id} "
			+ "")
	void acceptWorkRequest(int id);
}
