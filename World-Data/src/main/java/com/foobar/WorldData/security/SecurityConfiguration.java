package com.foobar.WorldData.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/city/**").hasRole("USER")
			.anyRequest().authenticated()
			.and().httpBasic()
			.authenticationEntryPoint(getAuthenticationEntryPoint());
	}
	
	@Bean
	private CustomBasicAuthenticationEntryPoint getAuthenticationEntryPoint()
	{
		return new CustomBasicAuthenticationEntryPoint();
	}
	
	
	
}
