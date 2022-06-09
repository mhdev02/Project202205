package com.project.backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@GetMapping("/")
	public String displayItems(Model model) {
		
//		List<ItemDto> items = itemService.getAll();
//		model.addAttribute("itemsList", items);
		
		return "home";
		
	}
	
	@GetMapping("/register")
	public String signUp() {
		return "register";
	}
	
	@GetMapping("/crawl")
	public String crawl() {
		return "crawling";
	}
	
}
