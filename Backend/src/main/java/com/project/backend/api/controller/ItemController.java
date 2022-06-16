package com.project.backend.api.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.api.model.request.ItemRequestModel;
import com.project.backend.api.model.response.ItemRest;
import com.project.backend.api.model.response.RequestStatus;
import com.project.backend.api.model.response.StatusModel;
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
	public ItemRest createItem(@RequestBody ItemRequestModel item, HttpServletRequest req) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		ItemDto itemDto = modelMapper.map(item, ItemDto.class);

		ItemDto savedItem = itemService.createItem(itemDto, req);
		ItemRest returnValue = modelMapper.map(savedItem, ItemRest.class);

		return returnValue;
	}
	
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ItemRest updateItem(@PathVariable String id, @RequestBody ItemRequestModel itemInfo) {

		ModelMapper modelMapper = new ModelMapper();

		ItemDto itemDto = modelMapper.map(itemInfo, ItemDto.class);

		ItemDto updatedItem = itemService.updateItem(id, itemDto);
		ItemRest returnValue = modelMapper.map(updatedItem, ItemRest.class);

		return returnValue;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public StatusModel deleteItem(@PathVariable String id) {
		StatusModel returnValue = new StatusModel();
		returnValue.setName(RequestName.DELETE.name());

		itemService.deleteItem(id);

		returnValue.setResult(RequestStatus.SUCCESS.name());
		return returnValue;
	}

}
