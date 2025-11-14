package com.consoleapp;

import java.util.ArrayList;
import java.util.Comparator;

public class Cart {

	// Array list of products
	private ArrayList<Product> products;
	
	//declair total price vaiable
	double total=0;

	// contructor
	public Cart() {
		products = new ArrayList<>();
	}
	

	// methods
	//Add product
	/*public void addProduct(Product product, int quantity, Category category) {
		for (Product pr : products) 
		{
			// if product present in cart then add quantity at the top of it
			if (pr.getId() == product.getId()) 
			{
				pr.setQuantity(pr.getQuantity() + quantity);
			}
			// add new product and quantity
			else
			{
				products.add(product);
				product.getCategory();
				product.setCategory(category);
				product.setQuantity(quantity);
				System.out.println("Product added to your cart : " + product.displayProduct());
			}
		}
	}*/
	public void addProduct(Product product, int quantity, Category category) {
	    boolean found = false;
	    for (Product pr : products) {
	        if (pr.getId() == product.getId()) {
	            pr.setQuantity(pr.getQuantity() + quantity);
	            found = true;
	            break;
	        }
	    }
	    if (!found) {
	        product.setCategory(category);
	        product.setQuantity(quantity);
			
	        products.add(product);
	        System.out.println("PRODUCT ADDED TO CART : " + product.displayProduct());
	    }
		
       
	}
	
	
	//remove product
	public void removeProduct(int id, int quantity)
	{
		// remove quantity
		for (Product pr : products) 
		{
			if (pr.getId() == id) 
			{
				if (pr.getQuantity() > quantity) 
				{
					pr.setQuantity(pr.getQuantity() - quantity);
					System.out.println( "Product quantity has removed and remaining products are : " + pr.getProductName()+ "  Remaining quantity: " + pr.getQuantity());
				}
				//remove product
				else 
				{
					products.remove(id);
					System.out.println(pr.getProductName() + " : has removed from the cart");
				}

			}
		}

	}
	
	//Display product
	public void displayCart_Product() {
		
		//System.out.println("Cart Items::::");		
		for(Product product : products ) {			
				
			System.out.println("CART ITEMS: " + product.displayProduct());

			//products.sort(Comparator.comparing(Product::getId));//short product by id
			
		}
	}

	//calculate total
	public double calculateTotal() {
		double total = 0;
		for(Product product : products) {
			total += product.getPrice() * product.getQuantity();			
		}		
		return total;
	}

	//Display cart items
	public String Cart_tostring() {
		return "Cart [products=" + products + ", total=" + calculateTotal() + "]";
	}

	//using defensive copy of products rather exposing the original product exposing the copy of product. Here avoiding exposing of the raw product list for safe coding
	public String displayCart_tostring() {
    StringBuilder sb = new StringBuilder("Cart Items:\n");

    new ArrayList<>(products).stream() // defensive copy
        .sorted(Comparator.comparing(Product::getId))
        .map(Product::displayProduct)
        .forEach(p -> sb.append(p).append("\n"));

    sb.append("Total: £").append(String.format("%.2f", calculateTotal()));
    return sb.toString();
}

	
	
}
