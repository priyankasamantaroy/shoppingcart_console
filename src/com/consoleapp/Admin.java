package com.consoleapp;

public final class Admin extends User {
	private String name;
	private Shop shop;


	public Admin(String name) {
		super(name);
		//this.name = name;
		this.shop = new Shop();
		}

	public Shop getShop(){
		return shop;
	}
	public String getName() {
		return name;
	}



}