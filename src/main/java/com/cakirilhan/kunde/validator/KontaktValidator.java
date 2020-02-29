package com.cakirilhan.kunde.validator;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cakirilhan.user.validator.EmailValidator;
import com.cakirilhan.web.dto.KontaktDto;

@Component
public class KontaktValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return KontaktDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		KontaktDto kontaktDto = (KontaktDto) target;
		
		ValidationUtils.rejectIfEmpty(errors, "vorname", "value.NotEmpty");
		ValidationUtils.rejectIfEmpty(errors, "nachname", "value.NotEmpty");
		
		
		if(!new EmailValidator().isValid(kontaktDto.getEmail(), null)) {
			errors.rejectValue("email", "value.Invalid.Email");
		}
		
		if(kontaktDto.getKunde() == null) {
			errors.rejectValue("kunde", "value.Kunde.Empty");
		}
	}
	

}
