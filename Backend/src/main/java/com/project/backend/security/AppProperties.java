package com.project.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	@Autowired
	private Environment env;

	public String getJWTSecret() {
		return env.getProperty("JWTSecret");
	}
	
	public String getFilePath() {
		return env.getProperty("filePath");
	}
}