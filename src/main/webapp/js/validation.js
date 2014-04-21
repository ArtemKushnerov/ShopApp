$(document).ready(function() {

	$.validator.addMethod("regex", function(value, element, regexp) {
		var re = new RegExp(regexp);
		return re.test(value);
	}, "Please check your input.");

	$("#productForm").validate();
	$('#productForm').find('.color').each(function() {
		$(this).rules('add', {
			required : true,
			messages : {
				required : "color is required."
			}
		});
	});

	$('#productForm').find('.date').each(function() {
		$(this).rules('add', {
			required : true,
			date : false,
			regex : "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-\\d{4}$",
			messages : {
				required : "date of issue is required.",
				regex : "invalid date format"
			}
		});
	});

	$('#productForm').find('.model').each(function() {
		$(this).rules('add', {
			required : true,
			regex : "^[a-zA-Z]{2}[0-9]{3}$",
			messages : {
				required : "model is required.",
				regex : "2 letters 3 digits"
			}
		});
	});

	$('#productForm').find('.producer').each(function() {
		$(this).rules('add', {
			required : true,
			messages : {
				required : "producer is required."
			}
		});
	});

	$('#productForm').find('.price').each(function() {
		$(this).rules('add', {
			required : true,
			regex : "^[0-9]*\\.?[0-9]*$",
			messages : {
				required : "price is required.",
				regex : "must be float number"
			}
		});
	});
});