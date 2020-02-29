package com.cakirilhan.domain.user.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.user.User;
import com.cakirilhan.web.dto.UserDto;

public interface UserRepositoryService {
	
	
	void save(User user);
	
	void deleteUser(long id);
	
    User findByUsername(String username);
    
    
       
    boolean deleteUsers(long[] ids);
    
    @Transactional
    void updateUser(User user);
    
    @Transactional
    void updatePassword(User user);
    
    Optional<String> getUserFirstNameLastname();
    

}
