package tuanlm.hr.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tuanlm.hr.app.filter.AuthenticationFilter;


/**
 * The Class SecurityConfig.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * Gets the authentication filter.
	 *
	 * @return the authentication filter
	 */
	@Bean
	public AuthenticationFilter getAuthenticationFilter() {
		return new AuthenticationFilter();
	}
	
	/**
	 * Gets the password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Authentication manager.
	 *
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	/**
	 * Configure.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
//		http.authorizeRequests()
//			.antMatchers("/api/login/**", "/", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
