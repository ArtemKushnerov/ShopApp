package com.epam.util;


public class ValidatorHolder {
	
	 public static boolean validate(Object val, String producer, String model, String color,
			String dateOfIssue, String price, boolean notInStock){
		
		return ((Validator)val).validate(producer, model, color, dateOfIssue, price, notInStock);
	}
	 
	 public static Object getModelError(Object val){
		 return  ((Validator)val).getErrors().get("modelError");
	 }
	 
	 public static Object getProducerError(Object val){
		 return  ((Validator)val).getErrors().get("producerError");
	 }
	 
	 public static Object getColorError(Object val){
		 return  ((Validator)val).getErrors().get("colorError");
	 }
	 
	 public static Object getDateError(Object val){
		 return  ((Validator)val).getErrors().get("dateError");
	 }
	 
	 public static Object getPriceError(Object val){
		 return  ((Validator)val).getErrors().get("priceError");
	 }
}
