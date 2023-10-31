package com.robertocosta.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {
	
	public static String decodeParam(String text) {
		
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static LocalDateTime convertDate(String textDate, LocalDateTime defaultValue) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		try {
			return LocalDateTime.parse(textDate, dtf);
		} catch (DateTimeParseException e) {
			return defaultValue;			
		}
	}

}
