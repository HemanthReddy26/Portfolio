package com.hcl.management.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	
	public static String date() {
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	        String date = LocalDateTime.now().format(format);  
	        return date;
	}
	
	public static String dateTime() {
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		 String dateTime = LocalDateTime.now().format(format);  
		 return dateTime;
	}

}
