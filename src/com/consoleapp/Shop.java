package com.consoleapp;
import java.util. *;

public class Shop {
	/*
	Create Shop class – to manage products /inventory
	•	Arry of products will be saved here
	•	loadProducts()
	•	dispalyAvailableProducts()
	•	searchproductByID()
*/	
	
	//declaire arraylist for products
	 ArrayList<Product> shopItems = new ArrayList<>();
	 
	 public Shop() {
			
	 }
	

	//methods to add new products by admin
	private void loadProducts()
	{
		shopItems.add(new Product("Laptop",899.99, 101));
		shopItems.add(new Product("macbook",1000.50,102));
		shopItems.add(new Product("iphone15",700.50,103));
		shopItems.add(new Product("iphone 16",900.50,104));
		shopItems.add(new Product("adidasshoe",250.50,105));
		shopItems.add(new Product("mkbag",500.50,106));
		//print all products
		for(Product p : shopItems) {
			System.out.println(p.displayProduct());
		}
		
	}
	public void availableProducts()
	{
		System.out.println("Avaliable products in shop : ");
		
		loadProducts();
			
		for(Product p:shopItems) {
			p.displayProduct();
		}
	}
	
	public Product searchProductById(int id)
	{
		for(Product p:shopItems) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
		
	}
	
	

}
