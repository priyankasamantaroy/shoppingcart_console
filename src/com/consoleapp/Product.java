package com.consoleapp;

public class Product {

	// properties declared private
	private String ProductName;
	private int id;
	private double price;
	private Category category;
	private int quantity;

	// constructor
	public Product(int id,String productName,double price,Category category,int quantity) {		
		this.id = id;
		this.ProductName = productName;	
		this.price = price;
		this.category = category;	
		this.quantity = quantity;
	}
	 // Added overloaded constructor to support (id, name, category, price, quantity)
    public Product(int id, String productName, Category category, double price, int quantity) {
        this(id, productName, price, category, quantity);
    }
	 // Optional constructor with default quantity
    public Product(int id, String productName, double price, Category category) {
        this(id, productName, price, category, 0);
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