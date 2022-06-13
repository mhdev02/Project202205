package com.project.backend.common;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.io.entity.UserEntity;
import com.project.backend.io.repository.UserRepository;
import com.project.backend.security.SecurityConstants;
import com.project.backend.service.ItemService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class Utils {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UserRepository userRepository;

	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public String generateId(int length) {
		return generateString(length);
	}

	private String generateString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}

		return new String(returnValue);
	}

	public static boolean hasTokenExpired(String token) {
		boolean returnValue = false;

		try {
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getJWTSecret()).parseClaimsJws(token)
					.getBody();

			Date tokenExpirationDate = claims.getExpiration();
			Date todayDate = new Date();

			returnValue = tokenExpirationDate.before(todayDate);
		} catch (ExpiredJwtException ex) {
			returnValue = true;
		}

		return returnValue;
	}
	
	public Map<String, String> bringUserIdAndHasJwtExpired(HttpServletRequest req) {
		
		Map<String, String> returnValue = new HashMap<>();
		
		String hasJwtExpired = "false";
		String token = "";
		String userEmail = "";
		String userId = "";
		
		try {
			String cookies = req.getHeader("Cookie");
			
			if (cookies != null) {
				String[] cookiesArr = cookies.split(";");
				for (int i = 0; i < cookiesArr.length; i++) {
					if ("jwt".equals(cookiesArr[i].trim().split("=")[0])) {
						token = cookiesArr[i].split("=")[1];
					}
				}
			} 
			if (token.length() > 0) {
				token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

				userEmail = Jwts.parser().setSigningKey(SecurityConstants.getJWTSecret()).parseClaimsJws(token).getBody()
					.getSubject();
				
				UserEntity userEntity = userRepository.findByEmail(userEmail);
				if (userEntity != null) {
					userId = userEntity.getUserId();
				}
			}
		} catch (ExpiredJwtException e) {
			hasJwtExpired = "true";
		} 
		
		returnValue.put("hasJwtExpired", hasJwtExpired);
		returnValue.put("token", token);
		returnValue.put("userId", userId);
		return returnValue;
	}

}