package com.foobar.WorldData.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={"com.foobar.WorldData.security"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired 
	private CustomBasicAuthenticationEntryPoint authEntryPoint;
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("mike").password("mikepass").roles("USER")
			.and()
			.withUser("jenny").password("jennypass").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		
		/**
		 * In the below configuration custom entryPoint and accessDeniedHandler are used
		 * Also for session creation policy "NEVER" is used , thus sesssion management will continue if and only if provided from other system
		 * 
		 **/
		 
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/city/**").hasRole("ADMIN")
			.and()
			.httpBasic().authenticationEntryPoint(authEntryPoint)
			.and()
			.exceptionHandling().accessDeniedHandler(getAuthAccessDeniedHandler())
			.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

		/**
		 * 
		 * In the below mentioned configuration no custom successHandler, failureHandlers, authEntryPoint are defined
		 * Also here Session are not managed and used thus authentication is required at every request
		 * 
		 **/		
//		http.httpBasic()
//			.and()
//			.authorizeRequests()
//			.antMatchers("/").permitAll()
//			.antMatchers("/city/**").hasRole("ADMIN")
//			.and()
//			.csrf().disable()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  
	}
	
	@Bean 
	public AuthSuccessHandler getSuccessHandler()
	{
		return new AuthSuccessHandler();
	}
	
	@Bean
	public AuthFailureHandler getFailurehandler()
	{
		return new AuthFailureHandler();
	}
	
	@Bean
	public AuthAccessDeniedHandler getAuthAccessDeniedHandler()
	{
		return new AuthAccessDeniedHandler();
	}
	
}
