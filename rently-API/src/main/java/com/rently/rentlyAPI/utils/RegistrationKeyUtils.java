package com.rently.rentlyAPI.utils;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RegistrationKeyUtils {
	
	public static String generateRegistrationKey(String role) {
		Random random = new Random();
		
		// Generate a random number in the range 10000 to 99999
		int randomNumber = 10000 + random.nextInt(90000);
		
		// Determine the prefix based on the role
		String prefix = "";
		if("RENTER".equalsIgnoreCase(role)) {
			prefix = "RE";
		} else if("OWNER".equalsIgnoreCase(role)) {
			prefix = "OW";
		} else {
			
			return "Invalid role";
		}
		
		return prefix + randomNumber;
	}
	
	
}
