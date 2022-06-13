package com.project.backend.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String cookies = req.getHeader("Cookie"); // SecurityConstants.HEADER_STRING
		String token = null;
		
		if (cookies != null) {
			String[] cookiesArr = cookies.split(";");
			for (int i = 0; i < cookiesArr.length; i++) {
				if ("jwt".equals(cookiesArr[i].trim().split("=")[0])) {
					token = cookiesArr[i].split("=")[1];
				}
			}
		}
		
		if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication;
		try {
			authentication = getAuthentication(req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws Exception {

		String cookies = request.getHeader("Cookie"); 
		String token = null;
		
		if (cookies != null) {
			String[] cookiesArr = cookies.split(";");
			for (int i = 0; i < cookiesArr.length; i++) {
				if ("jwt".equals(cookiesArr[i].trim().split("=")[0])) {
					token = cookiesArr[i].split("=")[1];
				}
			}
		} 
		
		try {
			if (token != null) {

				token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

				String user = Jwts.parser().setSigningKey(SecurityConstants.getJWTSecret()).parseClaimsJws(token).getBody()
						.getSubject();

				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}

				return null;
			}
			return null;
		} catch (ExpiredJwtException e) {
			// System.out.println("Expired JWT");
			return null;
		}

	}

}
