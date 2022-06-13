package com.project.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.backend.common.dto.ItemDto;
import com.project.backend.io.entity.UserEntity;
import com.project.backend.io.repository.UserRepository;
import com.project.backend.security.SecurityConstants;
import com.project.backend.service.ItemService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Controller
public class HomeController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	public String displayItemAndHome(Model model, HttpServletRequest req) {
		
		boolean hasJwtExpired = false;
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
				
				UserEntity userEntity =userRepository.findByEmail(userEmail);
				if (userEntity != null) {
					userId = userEntity.getUserId();
				}
				
			}

		} catch (ExpiredJwtException e) {
			hasJwtExpired = true;
		}
		
		List<ItemDto> items = itemService.getAll();
		model.addAttribute("itemsList", items);
		model.addAttribute("hasJwtExpired", hasJwtExpired);
		model.addAttribute("userId", userId);
		
		return "home";
		
	}
	
	@GetMapping("/register")
	public String signUp() {
		return "register";
	}
	
	@GetMapping("/signin")
	public String signIn() {
		return "signin";
	}
	
}