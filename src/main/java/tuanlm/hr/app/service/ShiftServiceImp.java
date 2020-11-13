package tuanlm.hr.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.ShiftMapper;
import tuanlm.hr.app.models.model.Shift;

@Service
@AllArgsConstructor
public class ShiftServiceImp implements ShiftService {
	
	/** The mapper. */
	private ShiftMapper mapper;

	/**
	 * Gets the all by store id.
	 *
	 * @param storeId the store id
	 * @return the all by store id
	 */
	@Override
	public List<Shift> getAllByStoreId(int storeId) {
		return mapper.getShiftListByStoreId(storeId);
	}

}
