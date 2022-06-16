package com.project.backend.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project.backend.common.dto.ItemDto;

public interface ItemService {
	
	ItemDto createItem(ItemDto item, HttpServletRequest req);
	
	List<ItemDto> getAll();

	List<ItemDto> getItems(String userId);

	ItemDto getItem(String itemId);
	
	ItemDto updateItem(String itemId, ItemDto item);
	
	void deleteItem(String itemId);

}
