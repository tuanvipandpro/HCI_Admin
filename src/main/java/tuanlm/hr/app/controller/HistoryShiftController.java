package tuanlm.hr.app.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import tuanlm.hr.app.service.HistoryShiftService;

/**
 * The Class HistoryShiftController.
 */
@RestController
@RequestMapping("/api/history")
@AllArgsConstructor
public class HistoryShiftController {

	/** The service. */
	private HistoryShiftService service;
	
	/**
	 * Store history shift work.
	 *
	 * @return result of add history shift work.
	 */
	@Operation(summary = "Lưu trữ thông tin chuyển ca làm việc", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/store-history-shift-work")
	public int storeHistoryShift(int workId, int empFrom, int empTo, LocalDateTime dateShift) {
		return service.storeHistoryShift(workId, empFrom, empTo, dateShift);
	}
}
