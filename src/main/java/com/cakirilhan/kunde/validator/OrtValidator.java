package com.cakirilhan.kunde.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.repository.service.OrtService;
import com.cakirilhan.web.dto.OrtDto;


@Component
public class OrtValidator implements org.springframework.validation.Validator {
	
	@Autowired
	OrtService ortService;
	
	

	@Override
	public boolean supports(Class<?> clazz) {
		return OrtDto.class.equals(clazz);
	}

	
	@Override
	public void validate(Object obj, Errors errors) {
		
		OrtDto ortDto = (OrtDto) obj;
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ortname", "value.NotEmpty");
		if(ortService.findByOrtname(ortDto.getOrtname()) != null) {
			if(ortService.findByOrtname(ortDto.getOrtname()).getOrtId() != ortDto.getOrtId()) {
			errors.rejectValue("ortname", "value.Doppel.ortForm.ortname"); }
		}
		
	}

}
