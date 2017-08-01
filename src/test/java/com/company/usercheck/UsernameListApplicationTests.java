package com.company.usercheck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.company.usercheck.domain.Result;
import com.company.usercheck.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsernameListApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(UsernameListApplicationTests.class);
	
	@Autowired
	private UserService userService;

	@Test
	public void testUserAlreadyExists() throws Exception{
		//already exists
		Result<Boolean, List<String>> result = userService.checkUserName("Maurice");
		logger.info("Result=" + result.getBoolean()+ " Suggestions= " + result.getSuggestions());
		assertFalse(result.getBoolean());	
	}
	
	@Test
	public void testUserDoesNotExistButRestrictedWord() throws Exception{
		//does not exist but has restricted word
		Result<Boolean, List<String>> result = userService.checkUserName("Pauldrunk");
		logger.info("Result=" + result.getBoolean()+ " Suggestions= " + result.getSuggestions());
		assertFalse(result.getBoolean());			
	}
	
	@Test
	public void testUserDoesNotExist() throws Exception{
		//does not exist
		Result<Boolean, List<String>> result = userService.checkUserName("PeterRod");
		logger.info("Result=" + result.getBoolean()+ " Suggestions= " + result.getSuggestions());
		assertTrue(result.getBoolean());			
	}
}
