package com.company.usercheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.usercheck.domain.RestrictedWord;
import com.company.usercheck.repository.RestrictedWordRepository;

/**
 * Bean to manage Restricted Words
 * @author hugo
 *
 */
@Service
public class RestrictedWordServiceImpl implements RestrictedWordService{

	@Autowired
	private RestrictedWordRepository restrictedWordRepository;
	
	@Override
	public List<RestrictedWord> loadAll() {
		return restrictedWordRepository.findAll();
	}

	@Override
	public void save(RestrictedWord restrictedWord) {
		restrictedWordRepository.save(restrictedWord);	
	}

	
}
