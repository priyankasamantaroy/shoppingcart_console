package com.consoleapp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//This class is created to manage products and inventories in the Shop
public class Shop implements ProductManager {
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
    //using lambda
    public Product searchProductById(int id) {
    return shopItems.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

     /*
     * Reserve quantity from inventory and return a product snapshot for cart.
     * Throws InvalidProductException when not enough stock or product missing.
     */
    public Product reserveProduct(int id, int quantity) throws InvalidProductException {
        Product p = searchProductById(id);
        if (p == null) {
            throw new InvalidProductException("Product not found: " + id);
        }
        if (quantity <= 0) {
            throw new InvalidProductException("Quantity must be positive");
        }
        synchronized (shopItems) {
            if (p.getQuantity() < quantity) { 
                throw new InvalidProductException("Not enough stock for product: " + id);
            }
            // deduct stock in inventory
            p.setQuantity(p.getQuantity() - quantity);
        }
        // return a fresh product object representing the reserved items
        return new Product(p.getId(), p.getProductName(), p.getPrice(), p.getCategory(), quantity);
    }


	// Admin: add product to shop inventory
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Error: Product cannot be null!");
            return;
        }
        shopItems.add(product);
    }

       // Admin : Add multiple products at a time (varargs)
     public void addProducts(Product... products) {
        if (products == null || products.length == 0) return;
        Product[] arr = products;
        Arrays.stream(arr).forEach(this::addProduct);
    }

    // Admin: remove product by id using lambda 
    public boolean removeProductById(int id) {
        return shopItems.removeIf(p -> p.getId() == id);
    }

    // Read-only copy of products
    public List<Product> getProducts() {
        return new ArrayList<>(shopItems);
    }

    /*
    predicate is using here to apply any given condition to product,
    if its returns true the product will stay in stream else will excluded from stream */ 
     @Override
    public List<Product> filterProducts(Predicate<Product> predicate) {
        return shopItems.stream().filter(predicate).collect(Collectors.toList());
    }

    // Return a small status line with today's date and item count
    public String inventorySnapshotDate() {
        LocalDate today = LocalDate.now();
        return "Inventory snapshot: " + ProductManager.formatDate(today) + " - total items: " + getTotalProducts();
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
   

    //Used for Admin access================================================
    // Add new product to store
    public void addProductToStore(int id, String name, double price, String category, int quantity) {
        // Convert string category (pased as param) to Category enum
        Category cat;
        try {
            cat = Category.valueOf(category.toUpperCase());
        } catch(IllegalArgumentException e) {
            System.out.println("Invalid category! Using ELECTRONICS as default.");
            cat = Category.ELECTRONICS;
        }
        
        Product newProduct = new Product(id, name, cat, price, quantity);
        addProduct(newProduct);
    }
    
    // Remove product from store
    public void removeProductFromStore(int productId) {        
        for(int i = 0; i < shopItems.size(); i++) {
            if(shopItems.get(i).getId() == productId) {
                shopItems.remove(i);
                System.out.println("Product ID " + productId + " removed from store.");
                return;
            }
        }
        System.out.println("Product ID " + productId + " not found!");
    }
    
    // Update product in store
    public void updateProductInStore(int productId, String newName, double newPrice, int newQuantity) {
        Product p = searchProductById(productId);
        if(p != null) {
            if(newName != null && !newName.trim().isEmpty()) {
                p.setProductName(newName);
            }
            if(newPrice > 0) {
                p.setPrice(newPrice);
            }
            if(newQuantity >= 0) {
                p.setQuantity(newQuantity);
            }
            System.out.println("Product ID " + productId + " updated successfully!");
        } else {
            System.out.println("Product ID " + productId + " not found!");
        }
    }

}
