package com.consoleapp;

import java.util.Scanner;


//This is the entry point for this console aopplication. this app will provide Customer and Admin menus to perom tasks
public class ConsoleApp {

    public static void main(String[] args) {	
        
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        // Create scanner object to get user input
        Scanner sc = new Scanner(System.in);
        
        try {
            // User name entry
            String userName;
            System.out.println("Enter Your Name: ");
            userName = sc.nextLine().trim();
            
            if(userName.isEmpty()) {
                System.out.println("Name cannot be empty!");
                sc.close();
                return;
            }
            
            // Verify User Type
            System.out.println("Which user are you? (customer or admin): ");
            String userType = sc.nextLine().trim().toLowerCase();
            
            // Validate user type
            if(!userType.equals("customer") && !userType.equals("admin")) {
                System.out.println("Invalid user type! Only 'customer' or 'admin' are allowed.");
                sc.close();
                return;
            }
            
            // Route to appropriate interface
            if(userType.equals("customer")) {
                customerInterface(sc, userName);
            } else if(userType.equals("admin")) {
                adminInterface(sc, userName);
            }
            
        } catch(Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close scanner
            sc.close();
        }
    }
    
    // ===================== CUSTOMER INTERFACE =====================
    private static void customerInterface(Scanner sc, String custName) {
        // Create customer instance
        Customer customer = new Customer(custName);
        
        // Create shop instance
        Shop shop = new Shop();
        
        // Welcome message
        System.out.println("\n******** Welcome to ShoppingCartConsole: " + customer.getFormattedName() + " ******* \n");
        
        // Main menu loop
        boolean continueShopping = true;
        while(continueShopping) {
            
            // Print options to select
            System.out.println(">> View Products/Catalogue: 1");
            System.out.println(">> Add Items to cart: 2"); 
            System.out.println(">> Display/View cart items and Itemized total: 3");
            System.out.println(">> Remove Product: 4");
            System.out.println(">> View TOTAL amount and Checkout  : 5");
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
                    try {
                        System.out.println("=== Add Items to Cart ===");
                        System.out.println("Enter product ID: ");
                        int pId = sc.nextInt();
                        
                        Product shopProduct = shop.searchProductById(pId);
                        if(shopProduct == null) {
                            System.out.println("Product not found!");
                            sc.nextLine();
                            break;
                        }
                        if (shopProduct.getQuantity() <= 0) {
                            System.out.println("Product is out of stock.");
                            break;
                        }
                        
                        // loop until user provides a valid quantity (or cancels by entering 0)
                        while (true) {
                            System.out.println("Enter quantity (available: " + shopProduct.getQuantity() + ", enter 0 to cancel): ");
                            int quantity = getValidInput(sc, 0, shopProduct.getQuantity());
                            if (quantity == 0) {
                                System.out.println("Add cancelled.");
                                break;
                            }
                            try {
                                Product reserved = shop.reserveProduct(pId, quantity);
                                customer.getCart().addProduct(reserved, quantity, reserved.getCategory());
                                System.out.println("Product added to cart successfully!");
                                break;
                            } catch (InvalidProductException ipe) {
                                // if stock changed between read and reserve, refresh and prompt again
                                System.out.println("Cannot add product: " + ipe.getMessage());
                                shopProduct = shop.searchProductById(pId);
                                if (shopProduct == null || shopProduct.getQuantity() <= 0) {
                                    System.out.println("Product is no longer available.");
                                    break;
                                }
                               
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error adding product: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;                
                // View cart and total
                case 3:
                    System.out.println("=== Your Cart ===");
                    customer.getCart().displayCart_Product();
                    System.out.println("TOTAL VALUE: £" + customer.getCart().calculateTotal());
                    break;
                
                // Update/Remove Product
                case 4:
                    try {
                        System.out.println("=== Remove Product from Cart ===");
                        customer.getCart().displayCart_Product();
                        
                        System.out.println("\nEnter Product ID to Remove: ");
                        int removeID = sc.nextInt();
                        
                        System.out.println("Enter Quantity to Remove: ");
                        int removeQuantity = getValidInput(sc, 1, 1000);
                        
                        customer.getCart().removeProduct(removeID, removeQuantity, shop);
                        
                        System.out.println("\n--- Remaining Products in Cart ---");
                        customer.getCart().displayCart_Product();
                        System.out.println("TOTAL VALUE: £" + customer.getCart().calculateTotal());
                    } catch(Exception e) {
                        System.out.println("Error removing product: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                
                // Checkout
                case 5:
                    double total = customer.getCart().calculateTotal();
                    if(total == 0) {
                        System.out.println("Your cart is empty!");
                    } else {
                        System.out.println("=== Checkout ===");
                        System.out.println("Final Total: £" + total);
                       // list purchased items and clear the cart
                        System.out.println("You have purchased:");
                        customer.getCart().clearAndReturnItems().forEach(p -> System.out.println(" - " + p.displayProduct()));
                        System.out.println("Thank you for your purchase!");
                    }
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
    }
    
    // ===================== ADMIN INTERFACE for admin menu and Flow =====================
    private static void adminInterface(Scanner sc, String adminName) {
        Shop shop = new Shop();
        Admin admin = new Admin(adminName);
        
        System.out.println("\n******** Welcome Admin: " + admin.getFormattedName() + " ******* \n");
        
        boolean continueAdmin = true;
        while(continueAdmin) {            
            System.out.println(">> View All Products in Store: 1");
            System.out.println(">> Add New Product to Store: 2");
            System.out.println(">> Remove Product from Store: 3");
            System.out.println(">> Update Product Details: 4");
            System.out.println(">> View Inventory Value: 5");
            System.out.println(">> Exit Admin Panel: 6");
            
            System.out.println("\nWhich option you want to select (1-6)?: ");            
            int option = getValidInput(sc, 1, 6);
            System.out.println("You have selected option: " + option + "\n");       

            switch(option) {
                
                // View all products
                case 1:
                    System.out.println("=== All Store Products ===");
                    shop.availableProducts();
                    break;
                
                // Add product
                case 2:
                    try {
                        System.out.println("=== Add New Product ===");
                        System.out.println("Enter Product ID: ");
                        int pId = sc.nextInt();
                        sc.nextLine(); 
                        
                        System.out.println("Enter Product Name: ");
                        String pName = sc.nextLine().trim();
                        
                        System.out.println("Enter Product Price: ");
                        double pPrice = sc.nextDouble();
                        sc.nextLine();
                        
                        System.out.println("Enter Product Category (ELECTRONICS, FASHION, GROCERY): ");
                        String pCategory = sc.nextLine().trim();
                        
                        System.out.println("Enter Product Stock Quantity: ");
                        int pStock = getValidInput(sc, 0, 10000);
                        
                        shop.addProductToStore(pId, pName, pPrice, pCategory, pStock);
                        System.out.println("Product added to store successfully!");
                    } catch(Exception e) {
                        System.out.println("Error adding product: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                
                // Remove product
                case 3:
                    try {
                        System.out.println("=== Remove Product ===");
                        shop.availableProducts();
                        System.out.println("Enter Product ID to Remove: ");
                        int removeId = sc.nextInt();
                        sc.nextLine();                        
                        shop.removeProductFromStore(removeId);
                    } catch(Exception e) {
                        System.out.println("Error removing product: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                
                // Update product
                case 4:
                    try {
                        System.out.println("=== Update Product ===");
                        shop.availableProducts();
                        System.out.println("Enter Product ID to Update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.println("Enter New Product Name (or press Enter to skip): ");
                        String newName = sc.nextLine().trim();
                        
                        System.out.println("Enter New Product Price (or enter 0 to skip): ");
                        double newPrice = sc.nextDouble();
                        
                        System.out.println("Enter New Stock Quantity (or enter -1 to skip): ");
                        int newStock = sc.nextInt();
                        sc.nextLine();
                        
                        shop.updateProductInStore(updateId, 
                            newName.isEmpty() ? null : newName, 
                            newPrice > 0 ? newPrice : -1, 
                            newStock >= 0 ? newStock : -1);
                    } catch(Exception e) {
                        System.out.println("Error updating product: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;
                
                // View inventory value
                case 5:
                    System.out.println("=== Inventory Information ===");
                    System.out.println("Total Products: " + shop.getTotalProducts());
                    System.out.println("Total Inventory Value: £" + String.format("%.2f", shop.getInventoryValue()));
                    break;
                
                // Exit
                case 6:
                    continueAdmin = false;
                    System.out.println("\nAdmin panel closed. Goodbye!");
                    break;
                
                default:
                    System.out.println("Invalid option!");
            }
            
            System.out.println();
        }
    }
    
    // ===================== HELPER METHOD =====================
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
}

