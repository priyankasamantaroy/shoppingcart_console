package com.consoleapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//This class is created to hold products and its quantities for customer  
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
	//Add product to Cart, if product present , increase the quantity
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
	       Product cartItem = new Product(product.getId(), product.getProductName(), product.getPrice(), category, quantity);
			products.add(cartItem);			
	        System.out.println("PRODUCT ADDED TO CART : " + product.displayProduct());
	    }	
       
	}
	
	
	// Remove product when quantity reached; if a Shop is provided,
	// Restore the removed quantity back to the shop inventory
	public void removeProduct(int id, int quantity, Shop shop) {
		for (int i = 0; i < products.size(); i++) {
			Product pr = products.get(i);
			if (pr.getId() == id) {
				int removedQty = 0;
				if (pr.getQuantity() > quantity) {
					pr.setQuantity(pr.getQuantity() - quantity);
					removedQty = quantity;
					System.out.println("Quantity updated for: " + pr.getProductName());
				} else {
					removedQty = pr.getQuantity();
					products.remove(i);
					System.out.println(pr.getProductName() + " removed from the cart");
				}

				// restore removed quantity back to shop inventory
				if (shop != null && removedQty > 0) {
					Product shopP = shop.searchProductById(id);
					if (shopP != null) {
						// existing product in shop: increase its stock
						synchronized (shopP) {
							shopP.setQuantity(shopP.getQuantity() + removedQty);
						}
					} else {
						// product not present in shop (rare) — add a new entry with restored stock
						shop.addProduct(new Product(pr.getId(), pr.getProductName(), pr.getPrice(), pr.getCategory(), removedQty));
					}
				}
				return;
			}
		}
		System.out.println("Product ID " + id + " not found in cart.");
	}	

	//Display cart product
	public void displayCart_Product() {		
		for(Product product : products ) {			
				
			System.out.println("CART ITEMS: " + product.displayProduct());
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

	//Using defensive copy of products rather exposing the original product exposing the copy of product. Here avoiding exposing of the raw product list for safe coding
	public String displayCart_tostring() {
    StringBuilder sb = new StringBuilder("Cart Items:\n");

    new ArrayList<>(products).stream() // defensive copy
        .sorted(Comparator.comparing(Product::getId))
        .map(Product::displayProduct)
        .forEach(p -> sb.append(p).append("\n"));

    sb.append("Total: £").append(String.format("%.2f", calculateTotal()));
    return sb.toString();
	}

	// Apply when checkout - Clear the cart and return the list of items
	public List<Product> clearAndReturnItems() {
		List<Product> snapshot = new ArrayList<>(products);
		products.clear();
		return snapshot;
	}	
	
}
