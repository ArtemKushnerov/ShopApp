package com.epam.presentation.form;

import org.apache.struts.action.ActionForm;
import org.jdom2.Document;

public class ProductForm extends ActionForm{
	
	private Document doc;

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	
}
