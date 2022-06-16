package com.project.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.api.model.response.ErrorMessages;
import com.project.backend.common.Utils;
import com.project.backend.common.dto.ImageDto;
import com.project.backend.exceptions.ImageServiceException;
import com.project.backend.io.entity.ImageEntity;
import com.project.backend.io.entity.ItemEntity;
import com.project.backend.io.repository.ImageRepository;
import com.project.backend.io.repository.ItemRepository;
import com.project.backend.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public ImageDto createImage(ImageDto image, String itemId) {

		ItemEntity item = itemRepository.findByItemId(itemId);

		ModelMapper modelMapper = new ModelMapper();
		ImageEntity imageEntity = modelMapper.map(image, ImageEntity.class);
		String imageId = utils.generateId(30);
		imageEntity.setImageId(imageId);
		imageEntity.setOriginalName(image.getOriginalName());
		imageEntity.setMimeType(image.getMimeType());
		imageEntity.setData(image.getData());
		imageEntity.setItem(item);

		ImageEntity savedImageEntity = imageRepository.save(imageEntity);
		
		item.setImage(savedImageEntity);
		
		itemRepository.save(item);
		
		ImageDto returnValue = new ModelMapper().map(savedImageEntity, ImageDto.class);

		return returnValue;
	}

	@Override
	public ImageDto getImage(String imageId) {

		ImageDto returnValue = null;

		ImageEntity imageEntity = imageRepository.findByImageId(imageId);

		if (imageEntity != null) {
			returnValue = new ModelMapper().map(imageEntity, ImageDto.class);
		}

		return returnValue;
	}
	
	@Override
	public ImageDto updateImage(ImageDto image, String imageId) {

		ImageEntity imageEntity = imageRepository.findByImageId(imageId);

		if (imageEntity == null)
			throw new ImageServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		imageEntity.setOriginalName(image.getOriginalName());
		imageEntity.setMimeType(image.getMimeType());
		imageEntity.setData(image.getData());

		ImageEntity updatedImageEntity = imageRepository.save(imageEntity);

		ImageDto returnValue = new ModelMapper().map(updatedImageEntity, ImageDto.class);

		return returnValue;

	}

	@Transactional
	@Override
	public void deleteImage(String imageId) {

		ImageEntity imageEntity = imageRepository.findByImageId(imageId);

		if (imageEntity == null)
			throw new ImageServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		ItemEntity item = itemRepository.findByItemId(imageEntity.getItem().getItemId());

		itemRepository.delete(item);
		imageRepository.delete(imageEntity);

	}

}