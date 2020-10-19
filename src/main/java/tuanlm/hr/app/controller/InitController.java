package tuanlm.hr.app.controller;

import javax.servlet.http.HttpServletResponse;

/**
 * The Class InitController.
 */
public class InitController {
	
	/**
	 * Inits the page. Go to Swagger
	 *
	 * @param response the response
	 * @return the string
	 */
	public String initPage(HttpServletResponse response) {
		return "redirect:swagger-ui.html";
	}
}
