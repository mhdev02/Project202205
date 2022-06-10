package com.project.backend.api.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.api.model.request.ItemRequestModel;
import com.project.backend.api.model.response.ItemRest;
import com.project.backend.common.dto.ItemDto;
import com.project.backend.service.ItemService;
import com.project.backend.service.UserService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ItemRest> getItems() {
		List<ItemRest> returnValue = new ArrayList<>();

		List<ItemDto> itemDto = itemService.getAll();

		Type listType = new TypeToken<List<ItemRest>>() {
		}.getType();
		returnValue = new ModelMapper().map(itemDto, listType);

		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public boolean createItem(@RequestBody ItemRequestModel item, @RequestHeader HttpHeaders headers) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		ItemDto itemDto = modelMapper.map(item, ItemDto.class);

		boolean returnValue = itemService.createItem(itemDto, headers);


		return returnValue;
	}

}
