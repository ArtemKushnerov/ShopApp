package com.epam.util;

import java.util.Map;

public class Validator {
	public static boolean validate(String producer, String model, String color,
			String dateOfIssue, String price, String notInStock,
			Object errors) {
		Map<String,String> err=(Map)errors;
		err.put("sorry", "sorry");
		return true;
	}
}