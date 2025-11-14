package com.consoleapp;

public final class Customer extends User {

	public  Cart cart;
	public String name;

	//constructor using super() calls parent constructor from User class
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
	 // Overriding parent method
    @Override
    public String toString() {
        var parentInfo = super.toString();  // super. - accessing parent method
        return parentInfo ;
    }

	
	
}