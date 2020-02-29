package com.cakirilhan.user.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakirilhan.domain.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	

}
