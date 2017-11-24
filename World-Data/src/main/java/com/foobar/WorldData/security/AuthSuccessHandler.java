package com.foobar.WorldData.security;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author Chinmay
 * 23-Nov-2017
 * 
 * Given class is responsible of what to do if authentication is successful
 * Bydefault it redirects to the requested URL
 * 
 * 
 * Note : we can even extend "SimpleUrlAuthenticationSuccessHandler" which is the parent class of the below extended class
 * 
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

}
