package com.cakirilhan.kunde.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.kunde.repository.service.VortOrtBetreuungService;


@Component
public class VorOrtBetreuungValidator implements Validator {
	
	@Autowired
	private VortOrtBetreuungService vortOrtBetreuungService;

	@Override
	public boolean supports(Class<?> clazz) {
		
		return VorOrtBetreuung.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		VorOrtBetreuung vorOB = (VorOrtBetreuung) target;
		
		if(vortOrtBetreuungService.doppelVorOB(vorOB.getUser().getId(), vorOB.getKunde().getKundeId(), vorOB.getOrt().getOrtId())) {
			errors.reject("");
			
			
		}
		
	}

}
