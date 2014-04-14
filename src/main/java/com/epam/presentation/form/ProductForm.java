package com.epam.presentation.form;

import org.apache.struts.action.ActionForm;
import org.jdom2.Document;

public class ProductForm extends ActionForm{
	
	private Document doc;
	private String catName;
	private String subcatName;
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public String getSubcatName() {
		return subcatName;
	}

	public void setSubcatName(String subcatName) {
		this.subcatName = subcatName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	
}
