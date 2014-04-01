package com.epam.util;

import java.util.Map;

public class Validator {
	public static boolean validate(String producer, String model, String color,
			String dateOfIssue, String price, String notInStock, Object errors) {
		Map<String, String> err = (Map<String, String>) errors;
		return validateProducer(producer, err) & validateColor(color, err)
				& validateModel(model, err) & validatePrice(price, err)
				& validateDate(dateOfIssue, err)
				& validateNotInStock(notInStock, err);
	}

	private static boolean validateProducer(String producer,
			Map<String, String> err) {
		return isEmpty(producer);
	}

	private static boolean validateModel(String model, Map<String, String> err) {
		return isEmpty(model)
				&& model.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]");
	}

	private static boolean validateColor(String color, Map<String, String> err) {
		return isEmpty(color);

	}

	private static boolean validateDate(String date, Map<String, String> err) {
		return isEmpty(date) && date.matches("YYYY-MM-DD");
	}

	private static boolean validatePrice(String price, Map<String, String> err) {
		return isEmpty(price)&& price.matches("[0-9]*\\.?[0-9]*");
	}

	private static boolean validateNotInStock(String notInStock,
			Map<String, String> err) {
		return Boolean.valueOf(notInStock);

	}

	static boolean isEmpty(String str) {
		return str.length() == 0 || str == null;
	}

}