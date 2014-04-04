package com.epam.util;

import java.util.HashMap;
import java.util.Map;

public class Validator {
	private Map<String, String> errors = new HashMap<String, String>();

	public boolean validate(String producer, String model, String color,
			String dateOfIssue, String price, boolean notInStock) {
		boolean priceIsValid = true;
		if (!notInStock)
			priceIsValid = validatePrice(price);
		return validateProducer(producer) & validateColor(color)
				& validateModel(model) & priceIsValid
				& validateDate(dateOfIssue);
	}

	private boolean validateProducer(String producer) {
		if (isEmpty(producer)) {
			errors.put("producerError", "field can not be empty");
			return false;
		} else
			return true;
	}

	private boolean validateModel(String model) {
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

	private boolean validateColor(String color) {
		if (isEmpty(color)) {
			errors.put("colorError", "field can not be empty");
			return false;
		} else
			return true;
	}

	private boolean validateDate(String date) {
		if (isEmpty(date)) {
			errors.put("dateError", "field can not be empty");
			return false;
		}
		if (!date.matches("^[0-3][0-9]-[0-1][0-9]-[0-9]{4}$")) {
			errors.put("dateError", "date must be in dd-MM-YYYY format");
			return false;
		}
		;
		return true;
	}

	private boolean validatePrice(String price) {
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

	private boolean isEmpty(String str) {
		return str.length() == 0 || str == null;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}