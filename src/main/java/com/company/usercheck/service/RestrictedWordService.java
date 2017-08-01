package com.company.usercheck.service;

import java.util.List;

import com.company.usercheck.domain.RestrictedWord;

/**
 * Interface for RestrictedWord Bean
 * @author hugo
 *
 */
public interface RestrictedWordService {
	
	List<RestrictedWord> loadAll();
	
	void save(RestrictedWord restrictedWord);
}
