package tuanlm.hr.app.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tuanlm.hr.app.mapper.HistoryShiftMapper;

@Service
@AllArgsConstructor
public class HistoryShiftServiceImp implements HistoryShiftService {

	/** The mapper. */
	private HistoryShiftMapper mapper;
	
	/**
	 * Store history shift work.
	 *
	 * @return result of add history shift work.
	 */
	@Override
	public int storeHistoryShift(int workId, int empFrom, int empTo, LocalDateTime dateShift) {
		return mapper.storeHistoryShiftWork(workId, empFrom, empTo, dateShift);
	}

}
