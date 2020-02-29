package com.cakirilhan.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Translator {

	private static MessageSource messageSource;
	
	
	public static String toLocale(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode, null, locale);
	}
}
