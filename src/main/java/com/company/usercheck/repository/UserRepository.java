package com.company.usercheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.usercheck.domain.User;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUserName(String userName);

}
