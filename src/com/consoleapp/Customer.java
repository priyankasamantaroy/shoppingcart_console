package com.consoleapp;

public final class Customer extends User {

//public class Customer{
	
	public  Cart cart;
	public String name;

	//constructor
	public Customer(String name) {
		super(name);
		//this.name = name;
		this.cart = new Cart();
			}

	//getters
	public Cart getCart() {
		return cart;
	}

	public String getName() {
		return name;
	}

	
	
}