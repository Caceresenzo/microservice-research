package caceresenzo.hello.common.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import caceresenzo.hello.common.security.filter.HeaderAuthenticationFilter;
import caceresenzo.hello.common.security.unauthorized.UnauthorizedEntryPoint;

@SuppressWarnings("deprecation")
@Import(UnauthorizedEntryPoint.class)
public class BaseApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	protected UnauthorizedEntryPoint unauthorizedEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.httpBasic().disable();
		http.headers().frameOptions().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint);
		http.authorizeHttpRequests().antMatchers("/swagger-ui/**").permitAll();
		
		configureFilters(http);
	}
	
	protected void configureFilters(HttpSecurity http) {
		http.addFilterBefore(new HeaderAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}