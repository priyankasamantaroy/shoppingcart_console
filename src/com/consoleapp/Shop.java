package com.consoleapp;
import java.util.ArrayList;
import java.util.List;

public class Shop {
	/*
	Create Shop class – to manage products /inventory
	•	Arry of products will be saved here
	•	loadProducts()
	•	dispalyAvailableProducts()
	•	searchproductByID()
*/	
	
	//declaire arraylist for products
	 private final List<Product> shopItems = new ArrayList<>();
	 
	 public Shop() {
			loadProducts();
	 }
	

	//methods to add new products by admin
	private void loadProducts()
	{
		 // String productName,Category category,double price, int id,int quantity
       shopItems.add(new Product(101, "Dell Inspiron 15", Category.ELECTRONICS, 749.99, 10));
        shopItems.add(new Product(102, "HP Pavilion", Category.ELECTRONICS, 649.50, 8));
        shopItems.add(new Product(103, "Sony Headphones", Category.ELECTRONICS, 129.99, 25));
        
        shopItems.add(new Product(201, "Men's Cotton Shirt", Category.FASHION, 29.99, 40));
        shopItems.add(new Product(202, "Women's Jeans", Category.FASHION, 49.99, 30));
        shopItems.add(new Product(203, "Sneakers", Category.FASHION, 89.99, 20));
        
        shopItems.add(new Product(301, "Basmati Rice 5kg", Category.GROCERY, 19.99, 100));
        shopItems.add(new Product(302, "Olive Oil 1L", Category.GROCERY, 12.99, 60));
        shopItems.add(new Product(303, "Whole Wheat Bread", Category.GROCERY, 3.99, 50));
		
	}
	 // Display all available products with better formatting
    public void availableProducts() {
        if (shopItems.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        
        System.out.println("\n========== AVAILABLE PRODUCTS IN SHOP ==========");
        System.out.println(String.format("%-6s | %-30s | %-15s | %-10s | %-8s", 
            "ID", "Product Name", "Category", "Price", "Stock"));
        System.out.println("================================================");
        
        for(Product p : shopItems) {
            System.out.println(String.format("%-6d | %-30s | %-15s | £%-9.2f | %-8d", 
                p.getId(), 
                p.getProductName(), 
                p.getCategory().name(), 
                p.getPrice(), 
                p.getQuantity()));
        }
        
        System.out.println("================================================\n");
    }
	
	//search product by ID
	public Product searchProductById(int id)
	{
		for(Product p:shopItems) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
		
	}

	    // Admin: add product to shop inventory
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Error: Product cannot be null!");
            return;
        }
        shopItems.add(product);
    }

    // Admin: remove product by id
    public boolean removeProductById(int id) {
        return shopItems.removeIf(p -> p.getId() == id);
    }

    // Read-only copy of products
    public List<Product> getProducts() {
        return new ArrayList<>(shopItems);
    }
	
	
	 // Display products with detailed information
    public void displayProductsDetailed() {
        if (shopItems.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        
        System.out.println("\n========== PRODUCT DETAILS ==========");
        for(Product p : shopItems) {
            System.out.println(p.displayProduct());
            System.out.println("------------------------------------");
        }
        System.out.println("=====================================\n");
    }

	// Get total product count
    public int getTotalProducts() {
        return shopItems.size();
    }

	 // Get total inventory value
    public double getInventoryValue() {
        return shopItems.stream()
            .mapToDouble(p -> p.getPrice() * p.getQuantity())
            .sum();
    }
	

}
