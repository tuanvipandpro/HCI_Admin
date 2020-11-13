package tuanlm.hr.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import tuanlm.hr.app.service.AccountService;
import tuanlm.hr.app.utils.JwtUtils;

/**
 * The Class AuthenticationFilter.
 */
public class AuthenticationFilter extends OncePerRequestFilter {
	
	/** The service. */
	@Autowired
	private AccountService service;

	/**
	 * Do filter internal.
	 *
	 * @param request the request
	 * @param response the response
	 * @param filterChain the filter chain
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication auth = null;
		String username = JwtUtils.getUserFromToken(request);
		
		if (username != null) {
			auth = new UsernamePasswordAuthenticationToken(username, null, service.loadUserByUsername(username).getAuthorities());
		}
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		response.setHeader("content-type", "application/json;charset=UTF-8");

		filterChain.doFilter(request, response);
	}

}
