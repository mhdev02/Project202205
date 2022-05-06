package com.project.backend.service;

import java.util.List;

import com.project.backend.shared.dto.ItemDto;

public interface ItemService {

	List<ItemDto> getItems(String userId);

	ItemDto getItem(String itemId);

}
