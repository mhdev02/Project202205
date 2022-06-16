package com.project.backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.backend.common.Utils;
import com.project.backend.common.dto.ItemDto;
import com.project.backend.service.ItemService;

@Controller
public class HomeController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	Utils utils;
	
	@GetMapping("/")
	public String displayItemAndHome(Model model, HttpServletRequest req) {
		
		Map<String, String> resultMap = utils.bringUserIdAndHasJwtExpired(req);
		
		List<ItemDto> items = itemService.getAll();
		model.addAttribute("itemsList", items);
		model.addAttribute("hasJwtExpired", resultMap.get("hasJwtExpired"));
		model.addAttribute("userId", resultMap.get("userId"));
		model.addAttribute("token", resultMap.get("token"));
		
		return "main/home";
		
	}
	
	@GetMapping("/register")
	public String signUp() {
		return "users/register";
	}
	
	@GetMapping("/signin")
	public String signIn() {
		return "users/signin";
	}
	
}