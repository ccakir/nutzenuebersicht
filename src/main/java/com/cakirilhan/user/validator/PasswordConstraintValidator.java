package com.cakirilhan.user.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>{

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		
		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				new LengthRule(8,30),
				new WhitespaceRule()
				
				));
		RuleResult result = validator.validate(new PasswordData(password));
		
		if(result.isValid()) {
			return true;
		}
		
	
		 
		
		
		return false;
	}

}
