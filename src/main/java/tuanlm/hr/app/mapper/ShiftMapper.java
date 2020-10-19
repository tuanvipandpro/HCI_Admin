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
			+ "		id 				AS id, "
			+ "		name			AS name, "
			+ "		start			AS start, "
			+ "		\"end\"			AS end, "
			+ "		max 			AS max, "
			+ "		active			AS active, "
			+ "		store_id		AS storeId "
			+ "FROM "
			+ "		shift "
			+ "WHERE "
			+ "		active = true "
			+ "		AND store_id = #{storeId} "
			+ "ORDER BY "
			+ "		id "
			+ "")
	List<Shift> getShiftListByStoreId(int storeId);
}
