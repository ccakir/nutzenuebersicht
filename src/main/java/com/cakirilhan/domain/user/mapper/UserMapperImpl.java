package com.cakirilhan.domain.user.mapper;

import org.springframework.stereotype.Service;

import com.cakirilhan.domain.user.User;
import com.cakirilhan.web.dto.UserDto;

@Service
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDto toUserDto(User user) {
		
		if(user == null) {
			return null;
		}
		
		UserDto userDto = new UserDto();
		
		if(user.getId() != null) {
			userDto.setId(user.getId());
		}
		
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setNachname(user.getNachname());
		userDto.setVorname(user.getVorname());
		userDto.setPassword(user.getPassword());
		userDto.setPasswordConfirm(user.getMatchingNewPassword());
		userDto.setRole(user.getRole());
		userDto.setOrt(user.getOrt());
		return userDto;
	}

	@Override
	public User toUser(UserDto userDto) {
		if(userDto == null) {
			return null;
		}
		
		User user = new User();
		
		if(userDto.getId() != null) {
			user.setId(userDto.getId());
		}
		
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setNachname(userDto.getNachname());
		user.setVorname(userDto.getVorname());
		user.setPassword(userDto.getPassword());
		user.setMatchingNewPassword(userDto.getPasswordConfirm());
		user.setRole(userDto.getRole());
		user.setOrt(userDto.getOrt());
		return user;
	}
	

}
