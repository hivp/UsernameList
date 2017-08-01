package com.company.usercheck.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.usercheck.domain.Result;
import com.company.usercheck.domain.User;
import com.company.usercheck.repository.UserRepository;
import com.company.usercheck.util.Constants;
import com.company.usercheck.util.Utils;

/**
 * Bean to manage Users
 * @author hugo
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepository; 

	@Override
	public List<User> loadAll() {
		return userRepository.findAll();
	}
	
	@Override
	public void save(User user) {
		userRepository.save(user);
	}	
	
	/**
	 * Checks userName as per requirements
	 */
	public Result<Boolean, List<String>> checkUserName(String userName) throws Exception{
		
		if(userName==null || userName.length() < Constants.USER_MIN_SIZE){
			//as per requirement throws an exception
			throw new Exception("User "+ userName +" should be at least 6 characters long");
		}
		
		List<String> alternatives = null;
		
		Boolean success = Boolean.TRUE;
		Boolean passRestrictedCheck = Boolean.FALSE;
		
		User user = new User(userName);
		
		if(userRepository.findOneByUserName(userName).isPresent()){
			success = Boolean.FALSE;
		}
		
		passRestrictedCheck = Utils.removeRestrictedWordFromUserName(user);
		
		if( !(success && passRestrictedCheck) ){
			alternatives = generateAlternateUserNames(user.getUserName());
		}
		
		return new Result<Boolean, List<String>>(success && passRestrictedCheck, alternatives);
	}
	
	/**
	 * Generate altertative userNames without restricted words
	 * @param userName
	 * @return a list of alternative userNames
	 */
	private List<String> generateAlternateUserNames(String userName){
		List<String> alternatives = new ArrayList<String>();

		User user = new User(userName);
		
		for(int index=0; index < 14; index++){
			User userAlternative = new User(user.getUserName()+RandomUtils.nextInt(1, 999));
			Utils.removeRestrictedWordFromUserName(userAlternative);
			
			//check if new generated userName if available
			if(userRepository.findOneByUserName(userAlternative.getUserName()).isPresent()){
				continue;
			}
			alternatives.add(userAlternative.getUserName());
		}
		
		//order
		Collections.sort(alternatives);
		
		return alternatives;
	}

}
