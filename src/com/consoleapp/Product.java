package com.consoleapp;

public class Product {

	// properties declared private
	private String ProductName;
	private int id;
	private double price;
	private Category category;
	private int quantity;

	// constructor
	public Product(String productName,/* Category category,*/ double price, int id) {		
		this.ProductName = productName;
		this.id = id;
		this.price = price;
		//this.category = category;
	}

	// encapsulation use getter setter to access private properties
	public void setProductName(String productName) {
		ProductName = productName;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductName() {
		return ProductName;
	}

	public int getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public Category getCategory() {
		return category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String displayProduct() {
		return "Product [ProductName=" + ProductName + ", id=" + id + ", price=" + price + ", category=" + category
				+ ", quantity=" + quantity + "]";
	}

	// java25 Adv OOP: use record - immutable data structure for product info OR
	// Summery
	/*
	 * public record productInfo(String productName, double price, Category
	 * category) {
	 * 
	 * }
	 */

}