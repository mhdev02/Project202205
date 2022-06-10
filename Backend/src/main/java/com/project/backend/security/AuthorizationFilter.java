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

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader("Cookie"); // SecurityConstants.HEADER_STRING
		
		if (header != null) {
			String[] cookiesArr = header.split(";");
			for (int i = 0; i < cookiesArr.length; i++) {
				if ("jwt".equals(cookiesArr[i].trim().split("=")[0])) {
					header = cookiesArr[i].split("=")[1];
				}
			}
		}

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String token = request.getHeader("Cookie"); // SecurityConstants.HEADER_STRING
		
		if (token != null) {
			String[] cookiesArr = token.split(";");
			for (int i = 0; i < cookiesArr.length; i++) {
				if ("jwt".equals(cookiesArr[i].trim().split("=")[0])) {
					token = cookiesArr[i].split("=")[1];
				}
			}
		} 
		

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
	}

}
