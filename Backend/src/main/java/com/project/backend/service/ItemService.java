package com.project.backend.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import com.project.backend.common.dto.ItemDto;
import com.project.backend.common.dto.UserDto;

public interface ItemService {
	
	boolean createItem(ItemDto item, @RequestHeader HttpHeaders headers);
	
	List<ItemDto> getAll();

	List<ItemDto> getItems(String userId);

	ItemDto getItem(String itemId);

}
