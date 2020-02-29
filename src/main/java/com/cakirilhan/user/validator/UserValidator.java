package com.cakirilhan.user.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cakirilhan.domain.user.User;
import com.cakirilhan.domain.user.service.UserRepositoryService;
import com.cakirilhan.web.dto.UserDto;



@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepositoryService userService;
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "value.NotEmpty");
        if (userDto.getUsername().length() < 6 || userDto.getUsername().length() > 10) {
            errors.rejectValue("username", "value.Size.userForm.username");
        }
        if (userService.findByUsername(userDto.getUsername()) != null  && userDto.getId() == null) {
            errors.rejectValue("username", "value.Duplicate.userForm.username");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
         if (userDto.getPassword().length() < 8 || userDto.getPassword().length() > 32  && userDto.getId() == null) {
            errors.rejectValue("password", "value.Size.userForm.password");
        }
         
        
        

        if (!userDto.getPasswordConfirm().equals(userDto.getPassword()) && userDto.getId() == null) {
            errors.rejectValue("matchingNewPassword", "value.Diff.userForm.passwordConfirm");
        }
        
        if (!new EmailValidator().isValid(userDto.getEmail(), null)) {
        	
        	errors.rejectValue("email", "value.Invalid.Email");
        	
        }
        
        if (!new PasswordConstraintValidator().isValid(userDto.getPassword(), null)  && userDto.getId() == null ) {
        	
        	errors.rejectValue("password", "value.Invalid.Password");
        	
        }
        
        if(userDto.getOrt() == null ) {
        	errors.rejectValue("ort", "value.Arbeitsort.0.gewaehlt");
        }
    }

	
}