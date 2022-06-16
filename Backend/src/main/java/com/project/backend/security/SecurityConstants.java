package com.project.backend.security;

import com.project.backend.AppContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 86400000; // 1 day
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String H2_CONSOLE = "/h2-console/**";

	public static String getJWTSecret() {
		AppProperties appProperties = (AppProperties) AppContext.getBean("AppProperties");
		return appProperties.getJWTSecret();
	}
	
	public static String getFilePath() {
		AppProperties appProperties = (AppProperties) AppContext.getBean("AppProperties");
		return appProperties.getFilePath();
	}

}
