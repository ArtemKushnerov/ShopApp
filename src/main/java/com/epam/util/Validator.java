package com.epam.util;

import java.util.Map;

public class Validator {
	public static boolean validate(String producer, String model, String color,
			String dateOfIssue, String price, boolean notInStock, Object err) {
		Map<String, String> errors = (Map<String, String>) err;
		boolean priceIsValid = true;
		if (!notInStock)
			priceIsValid = validatePrice(price, errors);
		return validateProducer(producer, errors)
				& validateColor(color, errors) & validateModel(model, errors)
				& priceIsValid & validateDate(dateOfIssue, errors);
	}

	private static boolean validateProducer(String producer,
			Map<String, String> errors) {
		if (isEmpty(producer)) {
			errors.put("producerError", "field can not be empty");
			return false;
		} else
			return true;
	}

	private static boolean validateModel(String model,
			Map<String, String> errors) {
		if (isEmpty(model)) {
			errors.put("modelError", "field can not be empty");
			return false;
		}
		if (!model.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")) {
			errors.put("modelError",
					"model must consist of two letters and three digits");
			return false;
		}
		;
		return true;
	}

	private static boolean validateColor(String color,
			Map<String, String> errors) {
		if (isEmpty(color)) {
			errors.put("colorError", "field can not be empty");
			return false;
		} else
			return true;
	}
	
	private static boolean validateDate(String date, Map<String, String> errors) {
		if (isEmpty(date)) {
			errors.put("dateError", "field can not be empty");
			return false;
		}
		if (!date
				.matches("^[0-3][0-9]-[0-1][0-9]-[0-9]{4}$")) {
			errors.put("dateError", "date must be in dd-MM-YYYY format");
			return false;
		}
		;
		return true;
	}

	private static boolean validatePrice(String price,
			Map<String, String> errors) {
		if (isEmpty(price)) {
			errors.put("priceError", "field can not be empty");
			return false;
		}
		if (!price.matches("[0-9]*\\.?[0-9]*")) {
			errors.put("priceError", "price must be float number ");
			return false;
		}
		;
		return true;
	}

	static boolean isEmpty(String str) {
		return str.length() == 0 || str == null;
	}

}