package com.project.backend.service;

import com.project.backend.common.dto.ImageDto;

public interface ImageService {
	
	ImageDto createImage(ImageDto item, String itemId);
	
	ImageDto getImage(String imageId);
	
	ImageDto updateImage(ImageDto image, String imageId);
	
	void deleteImage(String imageId);

}
