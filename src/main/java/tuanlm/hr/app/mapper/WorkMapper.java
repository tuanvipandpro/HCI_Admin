package tuanlm.hr.app.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tuanlm.hr.app.models.model.Work;
import tuanlm.hr.app.models.model.WorkManagement;
import tuanlm.hr.app.models.model.WorkStore;

/**
 * The Interface WorkMapper.
 */
@Mapper
public interface WorkMapper {
	
	/**
	 * Gets the next work.
	 *
	 * @param employeeId the employee id
	 * @param now the now
	 * @return the next work
	 */
	@Select(""
			+ "SELECT "
			+ "		W.id 						AS id, "
			+ "		W.\"employeeId\" 			AS employeeId, "
			+ "		W.\"storeId\"	 			AS storeId, "
			+ "		W.present		 			AS present, "
			+ "		W.\"shiftId\"	 			AS shiftId, "
			+ "		W.\"statusId\"	 			AS statusId, "
			+ "		W.note 						AS note, "
			+ "		W.start 					AS start, "
			+ "		W.\"end\"	 				AS end, "
			+ "		W.present_time				AS presentTime "			
			+ "FROM "
			+ "		work AS W "
			+ "WHERE "
			+ "		W.\"employeeId\" = #{employeeId} "
			+ "		AND W.start >= #{now} "
			+ "		AND W.active = true "
			+ "ORDER BY "
			+ "		W.start")
	List<Work> getNextWork(int employeeId, LocalDateTime now);	
	
	/**
	 * Gets the total work by date.
	 *
	 * @param employeeId the employee id
	 * @param from the from
	 * @param to the to
	 * @return the total work by date
	 */
	@Select("<script> "
			+ "SELECT "
			+ "		W.id 						AS id, "
			+ "		W.\"employeeId\" 			AS employeeId, "
			+ "		W.\"storeId\"	 			AS storeId, "
			+ "		W.present		 			AS present, "
			+ "		W.\"shiftId\"	 			AS shiftId, "
			+ "		W.\"statusId\"	 			AS statusId, "
			+ "		W.note 						AS note, "
			+ "		W.start 					AS start, "
			+ "		W.\"end\"	 				AS end, "
			+ "		W.present_time				AS presentTime "	
			+ "FROM "
			+ "		work AS W "
			+ "WHERE "
			+ "		W.\"employeeId\" = #{employeeId} "
			+ "		AND W.active = true "
			+ "<if test=\"from != null and to != null\"> "
			+ "		AND W.start <![CDATA[>=]]> #{from} "
			+ "</if> "
			+ "		AND W.\"end\" <![CDATA[<=]]> #{to} "
			+ "ORDER BY "
			+ "		W.start DESC "
			+ "<if test=\"from == null\"> "
			+ "		LIMIT 10 "
			+ "</if> "
			+ "</script>")
	List<Work> getTotalWorkByDate(int employeeId, LocalDateTime from, LocalDateTime to);
	
	/**
	 * Gets the work by employee.
	 *
	 * @param employeeId the employee id
	 * @param now the now
	 * @return the work by employee
	 */
	@Select(""
			+ "SELECT "
			+ "		W.id 						AS id, "
			+ "		W.\"employeeId\" 			AS employeeId, "
			+ "		W.\"storeId\"	 			AS storeId, "
			+ "		W.present		 			AS present, "
			+ "		W.\"shiftId\"	 			AS shiftId, "
			+ "		W.\"statusId\"	 			AS statusId, "
			+ "		W.note 						AS note, "
			+ "		W.start 					AS start, "
			+ "		W.\"end\"	 				AS end, "
			+ "		W.present_time				AS presentTime, "	
			+ "		S.name						AS storeNm "			
			+ "FROM "
			+ "		work AS W,"
			+ "		store AS S "
			+ "WHERE "
			+ "		W.\"employeeId\" = #{employeeId} "
			+ "		AND W.\"storeId\" = S.id "
			+ "		AND W.active = true "
			+ "		AND W.\"end\" <= #{now} "
			+ "ORDER BY "
			+ "		W.start DESC "
			+ "LIMIT "
			+ "		10 "
			+ "")
	List<WorkStore> getWorkByEmployee(int employeeId, LocalDateTime now);
	
	/**
	 * Gets the work now.
	 *
	 * @param employeeId the employee id
	 * @param storeId the store id
	 * @param datetime the datetime
	 * @return the work now
	 */
	@Select(""
			+ "SELECT "
			+ "		W.id 						AS id, "
			+ "		W.\"employeeId\" 			AS employeeId, "
			+ "		W.\"storeId\"	 			AS storeId, "			
			+ "		W.present		 			AS present, "
			+ "		W.\"shiftId\"	 			AS shiftId, "
			+ "		W.\"statusId\"	 			AS statusId, "
			+ "		W.note 						AS note, "
			+ "		W.start 					AS start, "
			+ "		W.\"end\"	 				AS end, "
			+ "		W.present_time				AS presentTime "			
			+ "FROM "
			+ "		work AS W "
			+ "WHERE "
			+ "		W.\"employeeId\" = #{employeeId} "
			+ "		AND W.\"storeId\" = #{storeId} "
			+ "		AND W.start <= #{datetime} "
			+ "		AND W.\"end\" >= #{datetime} "
			+ "		AND W.active = true ")
	Work getWorkNow(int employeeId, int storeId, LocalDateTime datetime);
	
	/**
	 * Gets the work by id.
	 *
	 * @param id the id
	 * @return the work by id
	 */
	@Select(""
			+ "SELECT "
			+ "		W.id 						AS id, "
			+ "		W.\"employeeId\" 			AS employeeId, "
			+ "		W.\"storeId\"	 			AS storeId, "			
			+ "		W.present		 			AS present, "
			+ "		W.\"shiftId\"	 			AS shiftId, "
			+ "		W.\"statusId\"	 			AS statusId, "
			+ "		W.note 						AS note, "
			+ "		W.start 					AS start, "
			+ "		W.\"end\"	 				AS end, "	
			+ "		W.present_time				AS presentTime "				
			+ "FROM "
			+ "		work AS W "
			+ "WHERE "
			+ "		W.id = #{id} "
			+ "")	
	Work getWorkById(int id);
	
	/**
	 * Gets the work maganement.
	 *
	 * @return the work maganement
	 */
	@Select(""
			+ "SELECT "
			+ "		W.id 						AS id, "
			+ "		E.name 						AS employeeNm, "
			+ "		W.\"employeeId\" 			AS employeeId, "
			+ "		W.\"storeId\"	 			AS storeId, "
			+ "		W.active					AS active, "
			+ "		S.name 						AS storeNm, "			
			+ "		W.present		 			AS present, "
			+ "		S1.name			 			AS shiftNm, "
			+ "		W.start 					AS start, "
			+ "		W.\"end\"	 				AS end, "
			+ "		W.present_time				AS presentTime "				
			+ "FROM "
			+ "		work AS W, "
			+ "		employee AS E, "
			+ "		store AS S, "
			+ "		shift AS S1 "
			+ "WHERE "
			+ "		W.\"employeeId\" = E.id "
			+ "		AND W.\"storeId\" = S.id "
			+ "		AND W.\"shiftId\" = S1.id "
			+ "ORDER BY "
			+ "		W.id "
			+ "")
	List<WorkManagement> getWorkMaganement();
	
	/**
	 * Present work.
	 *
	 * @param workId the work id
	 */
	@Update(""
			+ "UPDATE work "
			+ "SET present = true, present_time = #{presentTime} "
			+ "WHERE id = #{workId} ")
	void presentWork(int workId, LocalDateTime presentTime );
	
	/**
	 * Shift work.
	 *
	 * @param workId the work id
	 * @param shiftEmployeeId the shift employee id
	 */
	@Update(""
			+ "UPDATE work "
			+ "SET \"employeeId\" = #{shiftEmployeeId}, \"statusId\" = 2, note = 'Chuyển Ca' "
			+ "WHERE id = #{workId} ")
	void shiftWork(int workId, int shiftEmployeeId);
	
	/**
	 * Update work.
	 *
	 * @param mode the mode
	 * @param workId the work id
	 */
	@Update("<script>"
			+ "UPDATE work "
			+ "<if test = \"mode == 0\">"
			+ "		SET active = false "
			+ "</if> "
			+ "<if test = \"mode == 1\"> "
			+ "		SET active = true "
			+ "</if> "
			+ "WHERE "
			+ "		id = #{workId} "
			+ "</script>")
	void updateStatusWork(int mode, int workId);
	
	/**
	 * Creates the work.
	 *
	 * @param request the request
	 */
	@Insert(""
			+ "INSERT INTO work(\"employeeId\", active, present, \"shiftId\", \"statusId\", start, \"end\", \"storeId\") "
			+ "VALUES (#{employeeId}, true, false, #{shiftId}, 1, #{start}, #{end}, #{storeId}) "
			+ "")
	void createWork(int employeeId, int shiftId, LocalDateTime start, LocalDateTime end, int storeId);
	
	/**
	 * Check exist work.
	 *
	 * @param storeId the store id
	 * @param employeeId the employee id
	 * @param shiftId the shift id
	 * @param start the start
	 * @param end the end
	 * @return the work
	 */
	@Select(""
			+ "SELECT "
			+ "		W.id 						AS id, "
			+ "		W.\"employeeId\" 			AS employeeId, "
			+ "		W.\"storeId\"	 			AS storeId, "			
			+ "		W.present		 			AS present, "
			+ "		W.\"shiftId\"	 			AS shiftId, "
			+ "		W.\"statusId\"	 			AS statusId, "
			+ "		W.note 						AS note, "
			+ "		W.start 					AS start, "
			+ "		W.\"end\"	 				AS end "			
			+ "FROM "
			+ "		work AS W "
			+ "WHERE "
			+ "		W.\"storeId\" = #{storeId} "
			+ "		AND W.\"employeeId\" = #{employeeId} "
			+ "		AND W.\"shiftId\" = #{shiftId} "
			+ "		AND W.start = #{start} "
			+ "		AND W.\"end\" = #{end} "
			+ "")
	Work checkExistWork(int storeId, int employeeId, int shiftId, LocalDateTime start, LocalDateTime end);
}
