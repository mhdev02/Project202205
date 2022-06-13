package com.project.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import com.project.backend.api.model.response.ErrorMessages;
import com.project.backend.common.Utils;
import com.project.backend.common.dto.ItemDto;
import com.project.backend.exceptions.ItemServiceException;
import com.project.backend.io.entity.ItemEntity;
import com.project.backend.io.entity.UserEntity;
import com.project.backend.io.repository.ItemRepository;
import com.project.backend.io.repository.UserRepository;
import com.project.backend.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public boolean createItem(ItemDto item, @RequestHeader HttpHeaders headers) {
		
		String userId = "";
		
		try {
			String cookies = headers.getFirst("Cookie");
			String[] cookiesArr = cookies.split(";");
			for (int i = 0; i < cookiesArr.length; i++) {
				if ("userId".equals(cookiesArr[i].trim().split("=")[0])) {
					userId = cookiesArr[i].split("=")[1];
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}

		UserEntity user = userRepository.findByUserId(userId);

		ModelMapper modelMapper = new ModelMapper();
		ItemEntity itemEntity = modelMapper.map(item, ItemEntity.class);

		String itemId = utils.generateId(30);
		itemEntity.setItemId(itemId);
		itemEntity.setName(item.getName());
		itemEntity.setPrice(item.getPrice());
		itemEntity.setStock(item.getStock());
		itemEntity.setDescription(item.getDescription());
		itemEntity.setSeller(user);

		ItemEntity savedItemEntity = itemRepository.save(itemEntity);
		
		user.addItem(savedItemEntity);
		
		userRepository.save(user);

		return true;
	}
	
	@Override
	public List<ItemDto> getAll() {
		
		List<ItemDto> returnValue = new ArrayList<>();
		
		List<ItemEntity> items = itemRepository.findAll();
		
		for (ItemEntity item: items) {
			returnValue.add(new ModelMapper().map(item, ItemDto.class));
		}
		
		return returnValue;
	}

	@Override
	public List<ItemDto> getItems(String userId) {

		List<ItemDto> returnValue = new ArrayList<>();

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			return returnValue;

		Iterable<ItemEntity> items = itemRepository.findAllBySeller(userEntity);

		for (ItemEntity itemEntity : items) {
			returnValue.add(new ModelMapper().map(itemEntity, ItemDto.class));
		}

		return returnValue;

	}

	@Override
	public ItemDto getItem(String itemId) {

		ItemDto returnValue = null;

		ItemEntity itemEntity = itemRepository.findByItemId(itemId);

		if (itemEntity != null) {
			returnValue = new ModelMapper().map(itemEntity, ItemDto.class);
		}

		return returnValue;
	}
	
	@Override
	public ItemDto updateItem(String itemId, ItemDto item) {

		ItemEntity itemEntity = itemRepository.findByItemId(itemId);

		if (itemEntity == null)
			throw new ItemServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		itemEntity.setName(item.getName());
		itemEntity.setPrice(item.getPrice());
		itemEntity.setStock(item.getStock());
		itemEntity.setDescription(item.getDescription());

		ItemEntity updatedItemEntity = itemRepository.save(itemEntity);

		ItemDto returnValue = new ModelMapper().map(updatedItemEntity, ItemDto.class);

		return returnValue;

	}

	@Transactional
	@Override
	public void deleteItem(String itemId) {

		ItemEntity itemEntity = itemRepository.findByItemId(itemId);

		if (itemEntity == null)
			throw new ItemServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		UserEntity user = userRepository.findByUserId(itemEntity.getSeller().getUserId());

		// https://stackoverflow.com/questions/22688402/delete-not-working-with-jparepository
		user.getItems().remove(itemEntity);
		userRepository.save(user);
		itemRepository.delete(itemEntity);

	}

}