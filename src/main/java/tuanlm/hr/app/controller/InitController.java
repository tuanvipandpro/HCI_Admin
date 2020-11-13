package tuanlm.hr.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class InitController.
 */
@Controller
public class InitController {
	
	/**
	 * Inits the page. Go to Swagger
	 *
	 * @param response the response
	 * @return the string
	 */
	
	@RequestMapping("/swagger")
	public String initPage(HttpServletResponse response) {
		return "redirect:swagger-ui.html";
	}
}
