package com.project.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrawlingController {
	
	@Autowired
	private Environment env;
	
	@GetMapping("/crawl")
	public String crawl(Model model) {
	
		model.addAttribute("SERVER_IP", env.getProperty("SERVER_IP"));
		
		return "crawler/crawling";
	}

}