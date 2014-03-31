package com.epam.beans;

public class Product {

	private String model;
	private String producer;
	private String color;
	private double price;
	private boolean notInStock;
	private String dateOfIssue;

	public Product(String model, String color, String dateOfIssue,
			double price, String producer, boolean notInStock) {
		this.model=model;
		this.producer=producer;
		this.dateOfIssue =dateOfIssue;
		this.notInStock=notInStock;
		this.price=price;
		this.color= color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNotInStock() {
		return notInStock;
	}

	public void setNotInStock(boolean notInStock) {
		this.notInStock = notInStock;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

}
