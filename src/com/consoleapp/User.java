package com.consoleapp;

//java25 Adv OOP: use sealed class to restric inheritence to customer and admin
public sealed class User permits Customer, Admin {

	protected String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String name) {
		this.name = name;
	}

	public void displayUserInfo() {
		//
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}

/*
 abstract class User {
	 
	 protected String name;
	 
	 public User() {
		 
	 }

	void getName(){}
	 
	 
	
	
}*/