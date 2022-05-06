package com.project.backend.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

import com.project.backend.service.ItemService;
import com.project.backend.service.UserService;
import com.project.backend.shared.dto.ItemDto;
import com.project.backend.shared.dto.UserDto;
import com.project.backend.ui.model.request.UserRequestModel;
import com.project.backend.ui.model.response.ItemRest;
import com.project.backend.ui.model.response.RequestStatus;
import com.project.backend.ui.model.response.StatusModel;
import com.project.backend.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);

		returnValue = new ModelMapper().map(userDto, UserRest.class);

		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserRequestModel userInfo) throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userInfo, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);
		UserRest returnValue = modelMapper.map(createdUser, UserRest.class);

		return returnValue;
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserRequestModel userInfo) {

		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto = modelMapper.map(userInfo, UserDto.class);

		UserDto updateUser = userService.updateUser(id, userDto);
		UserRest returnValue = modelMapper.map(updateUser, UserRest.class);

		return returnValue;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public StatusModel deleteUser(@PathVariable String id) {
		StatusModel returnValue = new StatusModel();
		returnValue.setName(RequestName.DELETE.name());

		userService.deleteUser(id);

		returnValue.setResult(RequestStatus.SUCCESS.name());
		return returnValue;
	}

}
