package com.cakirilhan.kunde.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cakirilhan.domain.kunde.Vertrag;
import com.cakirilhan.domain.kunde.repository.service.VertragService;

@Component
public class VertragValidator implements Validator {
	
	@Autowired
	private VertragService vertragService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Vertrag.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Vertrag vertrag = (Vertrag) target ;
		
		
		
		
		
		if(vertrag.getOrt() == null) {
			errors.rejectValue("ort", "label.error.selectLocation");
		}
		
		else if(vertragService.findVertragByKundeAndOrt(vertrag.getKunde().getKundeId(), vertrag.getOrt().getOrtId())) {
			errors.rejectValue("ort", "message.error.duplicateLocationCustomer");
			
		} 
		
	}

}
