package com.project.backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.backend.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user);

	UserDto getUser(String email);

	UserDto getUserByUserId(String userId);

	UserDto updateUser(String userId, UserDto user);

	void deleteUser(String userId);

}