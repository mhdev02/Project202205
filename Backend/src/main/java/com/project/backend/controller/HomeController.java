package com.project.backend.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.backend.api.model.request.UserRequestModel;
import com.project.backend.common.dto.ItemDto;
import com.project.backend.common.dto.UserDto;
import com.project.backend.io.entity.UserEntity;
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
	
	@GetMapping("/item")
	public String updateItem(HttpServletRequest req) {
		return "updateItem";
	}
	
	@GetMapping("/crawl")
	public String crawl(Model model) {
	
		model.addAttribute("SERVER_IP", env.getProperty("SERVER_IP"));
		
		return "crawling";
	}
	
}
