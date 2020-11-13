package tuanlm.hr.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.StoreMapper;
import tuanlm.hr.app.models.model.Store;

/**
 * The Class StoreServiceImp.
 */
@Service
@AllArgsConstructor
public class StoreServiceImp implements StoreService {
	
	/** The mapper. */
	private StoreMapper mapper;

	/**
	 * Gets the all store.
	 *
	 * @return the all store
	 */
	@Override
	public List<Store> getAllStore() {
		return mapper.getAllStore();
	}

}
