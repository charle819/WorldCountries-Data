package com.foobar.WorldData.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
	
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		/**Need to send JSON response here**/
		
		PrintWriter printWriter = response.getWriter();
		printWriter.println("401 - Unauthorized : "+authException.getMessage());
		printWriter.flush();
		
	}
	

	
}