package com.project.backend.api.controller;

//import java.util.Base64;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.backend.api.model.response.ImageRest;
import com.project.backend.api.model.response.RequestStatus;
import com.project.backend.api.model.response.StatusModel;
import com.project.backend.common.ImageUtils;
import com.project.backend.common.dto.ImageDto;
import com.project.backend.service.ImageService;
import com.project.backend.service.ItemService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	ImageService imageService;

	@Autowired
	ItemService itemService;
	
	@Autowired
	ImageUtils imageUtils;

	@PostMapping(path = "/{itemId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ImageRest createImage(@RequestParam MultipartFile file, @PathVariable String itemId) throws Exception {
		
//		Base64.Encoder encoder = Base64.getEncoder();
//        byte[] fileEncoded = encoder.encode(file.getBytes());
//        String encodedImage = new String(fileEncoded, "UTF8");
		
		ModelMapper modelMapper = new ModelMapper();

		byte[] resizedImagebytes = imageUtils.imageResize(file);
        
        String encodedImage = Base64.encodeBase64String(resizedImagebytes); 
        
		ImageDto imageDto = new ImageDto();
		imageDto.setOriginalName(file.getOriginalFilename());
		imageDto.setMimeType(file.getContentType());
		imageDto.setData(encodedImage);

		ImageDto createdImage = imageService.createImage(imageDto, itemId);
		ImageRest returnValue = modelMapper.map(createdImage, ImageRest.class);

		return returnValue;
	}
	
	@PutMapping(path = "/{imageId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ImageRest updateImage(@RequestParam MultipartFile file, @PathVariable String imageId) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		
		byte[] resizedImagebytes = imageUtils.imageResize(file);
		
		String encodedImage = Base64.encodeBase64String(resizedImagebytes);
        
		ImageDto imageDto = new ImageDto();
		
		imageDto.setOriginalName(file.getOriginalFilename());
		imageDto.setMimeType(file.getContentType());
		imageDto.setData(encodedImage);

		ImageDto updatedImage = imageService.updateImage(imageDto, imageId);
		ImageRest returnValue = modelMapper.map(updatedImage, ImageRest.class);

		return returnValue;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public StatusModel deleteImage(@PathVariable String id) {
		StatusModel returnValue = new StatusModel();
		returnValue.setName(RequestName.DELETE.name());

		imageService.deleteImage(id);

		returnValue.setResult(RequestStatus.SUCCESS.name());
		return returnValue;
	}

}
