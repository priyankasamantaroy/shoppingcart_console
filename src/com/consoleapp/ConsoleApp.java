package com.consoleapp;

import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {	
        
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        // Create scanner object to get user input
        Scanner sc = new Scanner(System.in);
        
        try {
            // Customer name entry
            String custName;
            System.out.println("Enter Your Name: ");
            custName = sc.nextLine().trim();
            
            if(custName.isEmpty()) {
                System.out.println("Name cannot be empty!");
                sc.close();
                return;
            }
            
            // Verify User
            System.out.println("Which user are you? (customer or Admin): ");
            String userType = sc.nextLine().trim().toLowerCase();
            
            // Create user instance
            Customer customer = new Customer(custName);
            
            // Create shop instance
            Shop shop = new Shop();
            
            // Welcome message
            System.out.println("\n******** Welcome to ShoppingCartConsole: " + custName + " ******* \n");
            
            // Main menu loop
            boolean continueShopping = true;
            while(continueShopping) {
                
                // Print options to select
                System.out.println(">> View Products/Catalog: 1");
                System.out.println(">> Add Items to cart: 2"); 
                System.out.println(">> Display/View cart items and Itemized total: 3");
                System.out.println(">> Update/Remove Product: 4");
                System.out.println(">> Checkout: 5");
                System.out.println(">> EXIT the Console/System: 6");
                
                System.out.println("\nWhich option you want to select (1-6)?: ");
                
                int option = getValidInput(sc, 1, 6);
                System.out.println("You have selected option: " + option + "\n");
                
                switch(option) {
                    
                    // View products catalog
                    case 1:
                        System.out.println("=== View Available Products ===");
                        shop.availableProducts();
                        break;
                    
                    // Add products to cart
                    case 2:
                        addProductToCart(sc, shop, customer);
                        break;
                    
                    // View cart and total
                    case 3:
                        viewCart(customer);
                        break;
                    
                    // Update/Remove Product
                    case 4:
                        removeProductFromCart(sc, customer);
                        break;
                    
                    // Checkout
                    case 5:
                        checkout(customer);
                        break;
                    
                    // Exit
                    case 6:
                        continueShopping = false;
                        System.out.println("\nThank you for shopping! Goodbye!");
                        break;
                    
                    default:
                        System.out.println("Invalid option!");
                }
                
                System.out.println();
            }
            
        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close scanner
            sc.close();
        }
    }
    
    // Helper method to validate user input
    private static int getValidInput(Scanner sc, int min, int max) {
        while(true) {
            try {
                if(sc.hasNextInt()) {
                    int input = sc.nextInt();
                    if(input >= min && input <= max) {
                        return input;
                    } else {
                        System.out.println("Please enter a number between " + min + " and " + max + ":");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a valid number:");
                    sc.nextLine(); // Clear invalid input
                }
            } catch(Exception e) {
                System.out.println("Error reading input: " + e.getMessage());
                sc.nextLine(); // Clear buffer
            }
        }
    }
    
    // Method to add product to cart
    private static void addProductToCart(Scanner sc, Shop shop, Customer customer) {
        try {
            System.out.println("=== Add Items to Cart ===");
            System.out.println("Enter product ID: ");
            int pId = sc.nextInt();
            
            Product p = shop.searchProductById(pId);
            if(p == null) {
                System.out.println("Product not found!");
                return;
            }
            
            System.out.println("Enter quantity: ");
            int quantity = getValidInput(sc, 1, 1000);
            
            p.setQuantity(quantity);
            Category pCategory = p.getCategory();
            
            customer.cart.addProduct(p, quantity, pCategory);
            System.out.println("Product added to cart successfully!");
            
        } catch(Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
            sc.nextLine(); // Clear buffer
        }
    }
    
    // Method to view cart
    private static void viewCart(Customer customer) {
        System.out.println("=== Your Cart ===");
        customer.getCart().displayCart_Product();
        System.out.println("TOTAL VALUE: $" + customer.getCart().calculateTotal());
    }
    
    // Method to remove product from cart
    private static void removeProductFromCart(Scanner sc, Customer customer) {
        try {
            System.out.println("=== Remove Product from Cart ===");
            System.out.println("Current items in cart:");
            customer.getCart().displayCart_Product();
            
            System.out.println("\nEnter Product ID to Remove: ");
            int removeID = sc.nextInt();
            
            System.out.println("Enter Quantity to Remove: ");
            int removeQuantity = getValidInput(sc, 1, 1000);
            
            customer.getCart().removeProduct(removeID, removeQuantity);
            
            System.out.println("\n--- Remaining Products in Cart ---");
            customer.getCart().displayCart_Product();
            System.out.println("TOTAL VALUE: $" + customer.getCart().calculateTotal());
            
        } catch(Exception e) {
            System.out.println("Error removing product: " + e.getMessage());
            sc.nextLine(); // Clear buffer
        }
    }
    
    // Method for checkout
    private static void checkout(Customer customer) {
        System.out.println("=== Checkout ===");
        double total = customer.getCart().calculateTotal();
        
        if(total == 0) {
            System.out.println("Your cart is empty!");
            return;
        }
        
        System.out.println("Final Total: $" + total);
        System.out.println("Thank you for your purchase!");
        // Add payment processing logic here
    }
}