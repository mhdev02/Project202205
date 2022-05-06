package com.project.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.io.entity.ItemEntity;
import com.project.backend.io.entity.UserEntity;
import com.project.backend.io.repository.ItemRepository;
import com.project.backend.io.repository.UserRepository;
import com.project.backend.service.ItemService;
import com.project.backend.shared.dto.ItemDto;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<ItemDto> getItems(String userId) {

		List<ItemDto> returnValue = new ArrayList<>();

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			return returnValue;

		Iterable<ItemEntity> items = itemRepository.findAllByUserPurchases(userEntity);

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

}
