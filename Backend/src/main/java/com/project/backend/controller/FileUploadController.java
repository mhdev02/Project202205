package com.project.backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.backend.common.Utils;
import com.project.backend.security.SecurityConstants;

@Controller
public class FileUploadController {
	
	@Autowired 
	Utils utils;
	
	@GetMapping("/upload")
	public String registerItem(Model model, HttpServletRequest req) {
		
		Map<String, String> resultMap = utils.bringUserIdAndHasJwtExpired(req);
		
		model.addAttribute("hasJwtExpired", resultMap.get("hasJwtExpired"));
		model.addAttribute("userId", resultMap.get("userId"));
		model.addAttribute("token", resultMap.get("token"));
		model.addAttribute("filePath", SecurityConstants.getFilePath());
		
		return "upload";
	}
	
    @PostMapping("/upload")
    public String fileUploadToLocal(@RequestParam MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {

        String uuid = UUID.randomUUID().toString();
        // file upload to system
        File convertedFile = new File(SecurityConstants.getFilePath(), uuid + file.getOriginalFilename());
        file.transferTo(convertedFile);

        return "redirect:upload";
    }

}
