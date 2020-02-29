package com.cakirilhan.domain.user.service.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;













import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.user.Role;
import com.cakirilhan.domain.user.User;
import com.cakirilhan.domain.user.service.RoleService;
import com.cakirilhan.domain.user.service.UserRepositoryService;
import com.cakirilhan.user.repository.UserRepositiory;


@Service
public class UserServiceImpl implements UserRepositoryService{
	
	
	@Autowired
	private UserRepositiory userRepositiory;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EntityManager entityManager;

	

	@Override
	public User findByUsername(String username) {
		return userRepositiory.findByUsername(username);
	}



	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(user.getRole());
		System.out.println(user.getRole());
		userRepositiory.save(user);
		
	}



	@Override
	public void deleteUser(long id) {
		
		Optional < User > user = userRepositiory.findById(id);
		if(user.isPresent()) {
			userRepositiory.delete(user.get());
		}
		
	}



	



	@Override
	public void updateUser(User user) {
		
		
		userRepositiory.save(user);
		
	}



	@Override
	public boolean deleteUsers(long[] ids) {
		
		int i=0;
		while(i<ids.length) {
			
			Optional<User> user = userRepositiory.findById(ids[i]);
			if(user.isPresent()) {
				userRepositiory.delete(user.get());
			}
			i++;
			
		} 
		
		int k=0;
		while(k<ids.length) {
			
			Optional<User> user = userRepositiory.findById(ids[k]);
			if(user.isPresent()) {
				return false;
			}
			k++;
		}
		
		return true;
		
	}



	@Override
	public void updatePassword(User user) {
		
		try {
			entityManager.createQuery("UPDATE User u SET u.password=?1 WHERE u.username=?2")
			.setParameter(1, user.getPassword())
			.setParameter(2, user.getUsername())
			.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public Optional<String> getUserFirstNameLastname() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByUsernameReturnOptinalUser", User.class)
				.setParameter("username", username);
		
		User user = query.getSingleResult();
		
		
		
		
		
		
		
		return Optional.ofNullable(user.getVorname() + " " + user.getNachname());
	}



	


	




	

}
