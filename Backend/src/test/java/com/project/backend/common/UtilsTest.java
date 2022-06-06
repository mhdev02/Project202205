package com.project.backend.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilsTest {

	@Autowired
	Utils utils;

	@Test
	final void testGenerateId() {
		String userId = utils.generateId(30);
		String userId2 = utils.generateId(30);

		assertNotNull(userId);
		assertNotNull(userId2);

		assertTrue(userId.length() == 30);
		assertTrue(!userId.equalsIgnoreCase(userId2));
	}

	@Test
	final void testHasTokenExpired() {
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0LmNvbSIsImV4cCI6MTY1NDQ4ODU5NX0.RPdWgFlCdbCf-Ed8mPjM7g-qBhBpBBNtRTUL9B7viOwsU7fb3iFVs6bJvMKND4oNxKGg9iwIU05TnpdHadmygA";
		boolean trueOrFalse = Utils.hasTokenExpired(token);

		assertFalse(trueOrFalse);
	}
	
}
