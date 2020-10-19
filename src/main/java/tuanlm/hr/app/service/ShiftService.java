package tuanlm.hr.app.service;

import java.util.List;

import tuanlm.hr.app.models.model.Shift;

public interface ShiftService {
	
	/**
	 * Gets the all by store id.
	 *
	 * @return the all by store id
	 */
	List<Shift> getAllByStoreId(int storeId);
}
