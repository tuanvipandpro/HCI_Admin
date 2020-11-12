package tuanlm.hr.app.service;

import java.time.LocalDateTime;

public interface HistoryShiftService {
	
	/**
	 * Store history shift work.
	 *
	 * @return result of add history shift work.
	 */
	int storeHistoryShift(int workId, int empFrom, int empTo, LocalDateTime dateShift);
}
