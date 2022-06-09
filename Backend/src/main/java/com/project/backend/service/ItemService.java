package com.project.backend.service;

import java.util.List;

import com.project.backend.common.dto.ItemDto;

public interface ItemService {
	
	List<ItemDto> getAll();

	List<ItemDto> getItems(String userId);

	ItemDto getItem(String itemId);

}
