package com.project.backend.service;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import com.project.backend.common.dto.ItemDto;

public interface ItemService {
	
	boolean createItem(ItemDto item, @RequestHeader HttpHeaders headers);
	
	List<ItemDto> getAll();

	List<ItemDto> getItems(String userId);

	ItemDto getItem(String itemId);
	
	ItemDto updateItem(String itemId, ItemDto item);
	
	void deleteItem(String itemId);

}
