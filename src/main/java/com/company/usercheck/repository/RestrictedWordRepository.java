package com.company.usercheck.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.usercheck.domain.RestrictedWord;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface RestrictedWordRepository extends JpaRepository<RestrictedWord, Long>{

	Optional<RestrictedWord> findOneByWord(String word);
}
