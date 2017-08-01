package com.company.usercheck.util;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.company.usercheck.domain.RestrictedWord;
import com.company.usercheck.domain.User;
import com.company.usercheck.service.RestrictedWordService;
import com.company.usercheck.service.UserService;

/**
 * Utilities
 * @author hugo
 *
 */
public class Utils {
	
	private static Logger logger = LoggerFactory.getLogger(Utils.class);
	
	private static List<RestrictedWord> restrictedWords;
	
	/**
	 * Show users in DB
	 * @param userRepository
	 */
	public static void showUsersInDB(){
		UserService userService = StaticContextAccessor.getBean(UserService.class);		
		List<User> users = userService.loadAll();
		
		logger.info("****************** Users in DB *****************");
		for(User user : users){
			logger.info("User Name = " + user.getUserName());
		}
		logger.info("*******************************************************");
	}
	
	/**
	 * Show restricted words in DB
	 * @param context
	 */
	public static void showRestrictedWordsInDB(){
		RestrictedWordService restrictedWordService = StaticContextAccessor.getBean(RestrictedWordService.class);	
		List<RestrictedWord> words = restrictedWordService.loadAll();
		
		logger.info("****************** Restricted Words in DB *****************");
		for(RestrictedWord word : words){
			logger.info("Restricted Word = " + word.getWord());
		}
		logger.info("*******************************************************");		
	}
	
	/**
	 * Init DB. Uses only when does not have any information into DB
	 */
	public static void initDB(){
		UserService userService = StaticContextAccessor.getBean(UserService.class);
		userService.save(new User("Maurice"));
		userService.save(new User("Ronald"));
		userService.save(new User("Alexander"));
		userService.save(new User("Jackson"));
		userService.save(new User("Gabriel"));	
		
		RestrictedWordService restrictedWordService = StaticContextAccessor.getBean(RestrictedWordService.class);
		restrictedWordService.save(new RestrictedWord("cannabis"));
		restrictedWordService.save(new RestrictedWord("abuse"));
		restrictedWordService.save(new RestrictedWord("crack"));
		restrictedWordService.save(new RestrictedWord("damn"));
		restrictedWordService.save(new RestrictedWord("drunk"));
		restrictedWordService.save(new RestrictedWord("grass"));
	}	

	/**
	 * Gets all restricted words. Singleton for better performance
	 * @return
	 */
	public static List<RestrictedWord> getAllRestrictedWords(){
		if(restrictedWords==null){
			RestrictedWordService restrictedWordService = StaticContextAccessor.getBean(RestrictedWordService.class);
			restrictedWords = restrictedWordService.loadAll();
		}
		return restrictedWords;
	}
		
	
	/**
	 * Check whether userName contains any of the restricted words
	 * @param user
	 * @return
	 */
	public static Boolean userNameHasRestrictedWord(User user){
		List<RestrictedWord> restrictedWords = getAllRestrictedWords();
		
		//do not care case sensitive
		String userName = user.getUserName().trim().toLowerCase();
		
		for(RestrictedWord restrictedWord : restrictedWords){
			if( userName.indexOf(restrictedWord.getWord().trim().toLowerCase())!=-1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove restricted words from userName
	 * @param user with userName modified
	 * @return FALSE if has restricted word else TRUE
	 */
	public static Boolean removeRestrictedWordFromUserName(User user){
		Boolean passCheck = Boolean.TRUE;
		List<RestrictedWord> restrictedWords = getAllRestrictedWords();
		
		
		String userName = user.getUserName().trim();
		
		for(RestrictedWord restrictedWord : restrictedWords){
			
			if( userName.indexOf(restrictedWord.getWord().trim().toLowerCase())!=-1 || 
					userName.indexOf(restrictedWord.getWord().trim().toUpperCase())!=-1) {
				
				passCheck = Boolean.FALSE;
				userName = userName.replaceAll(restrictedWord.getWord().trim().toLowerCase(), "");
				userName = userName.replaceAll(restrictedWord.getWord().trim().toUpperCase(), "");
			}
		}
		
		user.setUserName(userName);
		return passCheck;
		
	}
	

}
