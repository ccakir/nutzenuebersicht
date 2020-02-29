package com.cakirilhan.audit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.cakirilhan.domain.user.User;
import com.cakirilhan.domain.user.service.UserRepositoryService;


public class SpringSecurityAuditorAware implements AuditorAware<User> {
	
	@Autowired
	private UserRepositoryService userService;

	@Override
	public Optional<User> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		  if (authentication == null || !authentication.isAuthenticated()) {
		   return null;
		  }
		  
		  return null;
	}

}
