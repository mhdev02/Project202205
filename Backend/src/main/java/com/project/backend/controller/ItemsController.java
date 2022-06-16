package com.project.backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.backend.common.Utils;
import com.project.backend.common.dto.ItemDto;
import com.project.backend.service.ItemService;
import com.project.backend.service.UserService;


@Controller
public class ItemsController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	Utils utils;
	
	@GetMapping("/item/register")
	public String registerItem(Model model, HttpServletRequest req) {
		
		Map<String, String> resultMap = utils.bringUserIdAndHasJwtExpired(req);
		
		model.addAttribute("hasJwtExpired", resultMap.get("hasJwtExpired"));
		model.addAttribute("userId", resultMap.get("userId"));
		model.addAttribute("token", resultMap.get("token"));
		
		return "items/registerItem";
	}
	
	@GetMapping("/item/update")
	public String updateItem(Model model, @RequestParam String itemId,  HttpServletRequest req) {
		
		Map<String, String> resultMap = utils.bringUserIdAndHasJwtExpired(req);
		ItemDto item = itemService.getItem(itemId);
		
		try {
			item.getImage().getImageId(); 
			model.addAttribute("imageId", item.getImage().getImageId());
		} catch(NullPointerException e) {
			model.addAttribute("imageId", "");
		}
		model.addAttribute("item", item);
		model.addAttribute("hasJwtExpired", resultMap.get("hasJwtExpired"));
		model.addAttribute("userId", resultMap.get("userId"));
		model.addAttribute("token", resultMap.get("token"));
		return "items/updateItem";
	}
	
	@GetMapping("/item")
	public String moreItems(Model model, @RequestParam String userId, @RequestParam String nickName, HttpServletRequest req) {
		
		Map<String, String> resultMap = utils.bringUserIdAndHasJwtExpired(req);
		
		List<ItemDto> items = itemService.getItems(userId);
		model.addAttribute("itemsList", items);
		model.addAttribute("nickName", nickName);
		model.addAttribute("hasJwtExpired", resultMap.get("hasJwtExpired"));
		model.addAttribute("userId", resultMap.get("userId"));
		model.addAttribute("token", resultMap.get("token"));
		return "items/moreItems";
	}
	
	@GetMapping("/seller")
	public String sellerItems(Model model, HttpServletRequest req) {
		
		Map<String, String> resultMap = utils.bringUserIdAndHasJwtExpired(req);
		
		List<ItemDto> items = itemService.getItems(resultMap.get("userId"));
		model.addAttribute("itemsList", items);
		model.addAttribute("hasJwtExpired", resultMap.get("hasJwtExpired"));
		model.addAttribute("userId", resultMap.get("userId"));
		model.addAttribute("token", resultMap.get("token"));

		return "items/sellerItems";
	}
	
}
