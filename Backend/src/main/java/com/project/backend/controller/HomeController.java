package com.project.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.backend.common.dto.ItemDto;
import com.project.backend.service.ItemService;
import com.project.backend.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/")
	public String displayItems(Model model) {
		
		List<ItemDto> items = itemService.getAll();
		model.addAttribute("itemsList", items);
		
		return "home.html";
		
	}
	
	@GetMapping("/register")
	public String signUp() {
		return "register.html";
	}
	
	@GetMapping("/signin")
	public String signIn() {
		return "signin.html";
	}
	
	@GetMapping("/item")
	public String updateItem(HttpServletRequest req) {
		return "updateItem.html";
	}
	
	@GetMapping("/crawl")
	public String crawl(Model model) {
	
		model.addAttribute("SERVER_IP", env.getProperty("SERVER_IP"));
		
		return "crawling.html";
	}
	
}
