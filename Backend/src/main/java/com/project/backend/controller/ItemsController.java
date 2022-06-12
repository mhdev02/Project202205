package com.project.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.backend.common.dto.ItemDto;
import com.project.backend.service.ItemService;

@Controller
public class ItemsController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/item")
	public String registerItem(HttpServletRequest req) {
		return "registerItem";
	}
	
	@GetMapping("/item/{id}")
	public String moreItems(Model model, @PathVariable String id) {
		List<ItemDto> items = itemService.getItems(id);
		model.addAttribute("itemsList", items);
		return "moreItems";
	}

}
