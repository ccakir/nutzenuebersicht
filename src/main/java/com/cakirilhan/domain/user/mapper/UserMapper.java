package com.cakirilhan.domain.user.mapper;

import org.mapstruct.Mapper;

import com.cakirilhan.domain.user.User;
import com.cakirilhan.web.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto toUserDto(User user);
	
	User toUser(UserDto userDto);
	
}
