package com.cakirilhan.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cakirilhan.domain.user.User;

public interface UserRepositiory extends JpaRepository<User, Long> {
	
	
	
	User findByUsername(String username);
	
	

}
