package com.cakirilhan.kunde.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.web.dto.KundeDto;


@Component
public class KundeValidator  implements Validator{

	@Autowired
	KundeRepository kundeRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return KundeDto.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		KundeDto kundeDto = (KundeDto) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "value.NotEmpty");
		if(kundeRepository.findByName(kundeDto.getName()) != null) {
			if(kundeRepository.findByName(kundeDto.getName()).getKundeId() != kundeDto.getKundeId()) {
				errors.rejectValue("name", "value.Doppel.kundeForm.name");
			}
		}
	}

}
