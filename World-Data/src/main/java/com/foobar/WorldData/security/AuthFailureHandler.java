package com.foobar.WorldData.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author Chinmay
 * 23-Nov-2017
 * 
 * Given class is responsible od wht to do if authentication fails,
 * Bydefault it will redirect to the URL mentioned in "defaultFailureURL" , if not mentioned will throw 401 status code
 */
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

}
