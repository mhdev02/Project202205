package com.project.backend.io.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.project.backend.io.entity.ItemEntity;
import com.project.backend.io.entity.UserEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
		createRecords();
	}

	@Transactional
	@Test
	final void testFindUserEntityByUserId() {
		String userId = "1";
		UserEntity user = userRepository.findByUserId(userId);

		assertNotNull(user);
		assertTrue(user.getItems().size() == 2);

		List<ItemEntity> userItems = user.getItems();

		ItemEntity firstItem = userItems.get(0);
		ItemEntity secondItem = userItems.get(1);

		assertEquals(firstItem.getName(), "Iphone 5");
		assertEquals(firstItem.getPrice().intValue(), 50000);
		assertEquals(firstItem.getStock().intValue(), 1);
		assertEquals(secondItem.getName(), "Iphone 6");
		assertEquals(secondItem.getPrice().intValue(), 60000);
		assertEquals(secondItem.getStock().intValue(), 1);

	}

	@Test
	final void testFindUserByEmail() {
		String email = "1@gmail.com";
		UserEntity user = userRepository.findByEmail(email);

		System.out.println(user);

		assertTrue(user.getEmail().equals(email));
	}

	private void createRecords() {

		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName("firstName1");
		userEntity.setLastName("lastName1");
		userEntity.setUserId("1");
		userEntity.setEncryptedPassword("password1");
		userEntity.setEmail("1@gmail.com");

		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setItemId("1");
		itemEntity.setName("Iphone 5");
		itemEntity.setPrice(50000);
		itemEntity.setStock(1);

		ItemEntity itemEntity2 = new ItemEntity();
		itemEntity2.setItemId("2");
		itemEntity2.setName("Iphone 6");
		itemEntity2.setPrice(60000);
		itemEntity2.setStock(1);

		List<ItemEntity> items = new ArrayList<>();
		items.add(itemEntity);
		items.add(itemEntity2);

		userEntity.setItems(items);

		userRepository.save(userEntity);

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setFirstName("firstName2");
		userEntity2.setLastName("lastName2");
		userEntity2.setUserId("2");
		userEntity2.setEncryptedPassword("password2");
		userEntity2.setEmail("2@gmail.com");

		ItemEntity itemEntity3 = new ItemEntity();
		itemEntity3.setItemId("3");
		itemEntity3.setName("Macbook 2017");
		itemEntity3.setPrice(1000000);
		itemEntity3.setStock(1);

		List<ItemEntity> items2 = new ArrayList<>();
		items2.add(itemEntity3);

		userEntity2.setItems(items2);

		userRepository.save(userEntity2);

	}

}