package com.cakirilhan.user.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target ({
	ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)

public @interface ValidEmail {
	
	String message() default "Invalid Email";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	

}
