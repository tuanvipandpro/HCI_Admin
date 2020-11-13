package tuanlm.hr.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tuanlm.hr.app.models.model.Shift;

@Mapper
public interface ShiftMapper {
	
	/**
	 * Gets the shift list by store id.
	 *
	 * @return the shift list by store id
	 */
	@Select(""
			+ "SELECT "
			+ "		S.id 				AS id, "
			+ "		S.name				AS name, "
			+ "		S.start				AS start, "
			+ "		S.\"end\"			AS end, "
			+ "		S.max 				AS max, "
			+ "		S.active			AS active, "
			+ "		S.store_id			AS storeId, "
			+ "		ST.name				AS storeNm "
			+ "FROM "
			+ "		shift AS S, "
			+ "		store AS ST "
			+ "WHERE "
			+ "		S.active = true "
			+ "		AND ST.active = true "
			+ "		AND S.store_id = ST.id "
			+ "		AND S.store_id = #{storeId} "
			+ "ORDER BY "
			+ "		id "
			+ "")
	List<Shift> getShiftListByStoreId(int storeId);
}
