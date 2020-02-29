package com.cakirilhan.audit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import com.cakirilhan.domain.user.service.UserRepositoryService;

public class AuditorAwareImpl implements AuditorAware<String> {
	
	@Autowired
	UserRepositoryService userRepositoryService;

	@Override
	public Optional<String> getCurrentAuditor() {
		
		return userRepositoryService.getUserFirstNameLastname();
	}

}
