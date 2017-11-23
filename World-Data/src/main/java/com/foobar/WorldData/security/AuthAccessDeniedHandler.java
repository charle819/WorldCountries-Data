package com.foobar.WorldData.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AuthAccessDeniedHandler implements AccessDeniedHandler{

	private static final Logger LOGGER = Logger.getLogger(AuthAccessDeniedHandler.class.getName());
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null)
		{
			LOGGER.warning("The user : "+auth.getName()+ " attemped to access the URL : "+request.getRequestURI());
		}
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		/**Need to send JSON response here**/
		
		PrintWriter printWriter = response.getWriter();
		printWriter.println("403 - Forbidden for user  : "+auth.getName()+" : "+accessDeniedException.getMessage());
		printWriter.flush();
	}

}
