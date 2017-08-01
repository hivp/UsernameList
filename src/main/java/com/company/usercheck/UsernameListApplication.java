package com.company.usercheck;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.company.usercheck.domain.Result;
import com.company.usercheck.service.UserService;
import com.company.usercheck.util.Utils;

@SpringBootApplication
public class UsernameListApplication {

	private static Logger logger = LoggerFactory.getLogger(UsernameListApplication.class);
	
	
	public static void main(String[] args) throws Exception{
		
		
		ConfigurableApplicationContext context = SpringApplication.run(UsernameListApplication.class, args);		
		UserService userService = context.getBean(UserService.class);
		
		//Uses only when does not have info in DB. It is stored in usercheck/database/testdb.mv.db
		//Utils.initDB();
		
		Utils.showUsersInDB();
		
		Utils.showRestrictedWordsInDB();
		
		try{
			//Tests. Also JUnit tests are implemented in UsernameListApplicationTests
			
			//already exists
			Result<Boolean, List<String>> result = userService.checkUserName("Maurice");
			logger.info("Result=" + result.getBoolean()+ " Suggestions= " + result.getSuggestions());
			
			//does not exist but has restricted word
			Result<Boolean, List<String>> result2 = userService.checkUserName("Pauldrunk");
			logger.info("Result=" + result2.getBoolean()+ " Suggestions= " + result2.getSuggestions());
			
			//does not exist
			Result<Boolean, List<String>> result3 = userService.checkUserName("PeterRod");
			logger.info("Result=" + result3.getBoolean()+ " Suggestions= " + result3.getSuggestions());	
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	}
	
}
