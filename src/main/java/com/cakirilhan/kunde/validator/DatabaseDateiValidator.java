package com.cakirilhan.kunde.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cakirilhan.domain.kunde.DatabaseDatei;
import com.cakirilhan.domain.kunde.repository.service.DatabaseDateiService;


@Component
public class DatabaseDateiValidator implements Validator {
	
	@Autowired
	private DatabaseDateiService databaseDateiService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DatabaseDatei.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		DatabaseDatei datei = (DatabaseDatei) target;
		
		if(databaseDateiService.findByFileName(datei.getFileName())) {
			errors.rejectValue("file1", "error.duplicateFile");
			
		}
		
	}

}
