package com.cakirilhan.user.validator;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cakirilhan.domain.user.User;
import com.cakirilhan.domain.user.service.SecurityService;
import com.cakirilhan.domain.user.service.SecurityServiceImpl;
import com.cakirilhan.domain.user.service.UserRepositoryService;
import com.cakirilhan.web.dto.PasswordDto;

@Component
public class PasswordMatchesValidator implements Validator {

	@Autowired
	private UserRepositoryService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordDto passwordDto = (PasswordDto) target;
		final String currentUserName = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		User user = userService.findByUsername(currentUserName);


		// Ob aktuelles Kennwort richtig ist.
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (!passwordEncoder.matches(passwordDto.getPassword(),
				user.getPassword())) {
			errors.rejectValue("password", "value.Passwort.alt.fehler");
		}

		// Ende

		// Neues Kennwort Kontrolle

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"NotEmpty");
		if (passwordDto.getNewPassword().length() < 8
				|| passwordDto.getNewPassword().length() > 32) {
			errors.rejectValue("newPassword", "value.Size.userForm.password");
		}

		if (!new PasswordConstraintValidator().isValid(
				passwordDto.getNewPassword(), null)) {

			errors.rejectValue("newPassword", "value.Invalid.Password");

		}

		// Ende

		// ob neues Kennwort überstimmen.

		if (!passwordDto.getNewPassword().equals(
				passwordDto.getMatchingNewPassword())) {
			errors.rejectValue("matchingNewPassword",
					"value.Diff.userForm.passwordConfirm");
		}

		// Ende

	}

}
