package com.consoleapp;

public class Product {

	// properties declared private using concept of encaptulation
	private String ProductName;
	private int id;
	private double price;
	private Category category;
	private int quantity;

	//Flexible constructor bodies to instanciate/create produt in different ways
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
	public void setPrice(double price) {
		this.price = price;
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
	 
	 // New: convert to immutable record copy
    public ProductRecord toRecord() {
        return new ProductRecord(this.id, this.ProductName, this.price, this.category, this.quantity);
    }

	//snapshot view from immutable record
	record ProductRecord(int id, String ProductName, double price, Category category, int quantity){};
}

	/*To freeze the state of above product class , we can use this immutableProduct class
	*This immutable product class has no setters , so once constructed , its state cannot be changed */
	
	final class ImmutableProduct {
	private final int id;
	private final String name;
	private final double price;
	private final Category category;
	private final int quantity;

	public ImmutableProduct(int id, String name, double price, Category category, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
	}

	public int getId() { return id; }
	public String getName() { return name; }
	public double getPrice() { return price; }
	public Category getCategory() { return category; }
	public int getQuantity() { return quantity; }

}