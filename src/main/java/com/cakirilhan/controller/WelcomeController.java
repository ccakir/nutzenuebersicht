package com.cakirilhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cakirilhan.user.repository.UserRepositiory;


@Controller
public class WelcomeController {
	
	@Autowired
    private UserRepositiory userRepositiory;

	@RequestMapping(value = {"/", "/welcome"} , method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		
		model.put("username", getLoggedinUserName());
		model.put("users", userRepositiory.findAll());
		
		return "user/listUsers";
	}

	private Object getLoggedinUserName() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}
