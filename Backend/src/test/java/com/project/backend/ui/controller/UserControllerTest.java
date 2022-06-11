package com.project.backend.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.project.backend.api.controller.UserController;
import com.project.backend.api.model.response.UserRest;
import com.project.backend.common.dto.ItemDto;
import com.project.backend.common.dto.UserDto;
import com.project.backend.service.impl.UserServiceImpl;

public class UserControllerTest {
	
	@InjectMocks  
	UserController userController;
	
	@Mock
	UserServiceImpl userService;
	
	UserDto userDto;
	
	final String userId = "ZdRnAHjBmVgH2KBmhaRNAaTaDyD9t0";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); 		
		
		ItemDto itemDto = new ItemDto();
		List<ItemDto> items = new ArrayList<>();
		items.add(itemDto);
		
		userDto = new UserDto();
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setEmail("a@gmail.com");
        userDto.setUserId(userId);
        userDto.setItems(items);
        userDto.setPassword("AHjaBmVgH2");
        userDto.setNickName("aa");
		
	}
	
	@Test
	final void testGetUser() {
	    when(userService.getUserByUserId(anyString())).thenReturn(userDto);	
	    
	    UserRest userRest = userController.getUser(userId);
	    
	    assertNotNull(userRest);
	    assertEquals(userId, userRest.getUserId());
	    assertEquals(userDto.getEmail(), userRest.getEmail());
	    assertEquals(userDto.getFirstName(), userRest.getFirstName());
	    assertEquals(userDto.getLastName(), userRest.getLastName());
	    assertEquals(userDto.getNickName(), userRest.getNickName());
	    assertTrue(userDto.getItems().size() == userRest.getItems().size());
	}

}