package tuanlm.hr.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tuanlm.hr.app.models.model.Store;

@Mapper
public interface StoreMapper {
	
	/**
	 * Gets the all store.
	 *
	 * @return the all store
	 */
	@Select(""
			+ "SELECT "
			+ "		id				AS id, "
			+ "		name			AS name, "
			+ "		address			AS address, "
			+ "		phone			AS phone, "
			+ "		email			AS email, "
			+ "		fax				AS fax, "
			+ "		active 			AS active "
			+ "FROM "
			+ "		store "
			+ "WHERE "
			+ "		active = true "
			+ "ORDER BY "
			+ "		id "
			+ "")
	List<Store> getAllStore();
}
