package com.project.backend.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class) // // loads up spring context(property files etc).  @RunWith is for JUnit 4. @ExtendWith is for JUnit 5
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
		
		assertTrue(userId.length() == 29);
		assertTrue(!userId.equalsIgnoreCase(userId2));
	}

}
