package com.foobar.WorldData.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * @author Chinmay
 * 23-Nov-2017
 * 
 * Given class is used for deciding what to do if logout is successfull,
 * Bydefault it will redirect to LoginPage URL,
 * Here we are simply sending 200 Status code
 */
@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
	
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().flush();
	}

}
