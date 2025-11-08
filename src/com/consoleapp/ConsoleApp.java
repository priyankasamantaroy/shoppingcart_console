
package com.consoleapp;

import java.util.Scanner;

public class ConsoleApp {

	public static void main(String[] args) {		
		
				// create scanner object to get user input
				Scanner sc = new Scanner(System.in);

				// Customer name entry
				String custName;
				System.out.println("Enter Your Name : ");
				custName = sc.nextLine();
				
				//verify User
				System.out.println("Which user are you? customer or Admin? : ");
				custName = sc.nextLine();
				
				//create user instance
				Customer customer = new Customer(custName);
				
				//create shop instance
				Shop shop = new Shop();		
				
		    			
				//product object declaired globally
				Product p;
				
				
				// welcome message
				System.out.println("******** Welcome to ShoppingCartConsole : " + custName + " ******* ");			
				
			    			
				// print options to select
				System.out.println(">> View Products/Catalog :  1");
				System.out.println(">> Add Items to cart :  2"); 
				System.out.println(">> Display/View cart items and Itemized total : 3");
				System.out.println(">> Update/Remove Product: 4");
				System.out.println(">> Checkout : 5");
				System.out.println(">> EXIT the Console/System : 6");
			    
				
				// Asking for user input to select options
				System.out.println("Which Option you want to select: 1,2,3,4,5,6 ? : ");

				// taking and storing input from user
				int option = sc.nextInt();

				//String message = STR."you have selected option : \{option}";
				System.out.println("You have selected option : " + option);
				
				// view products catalog ========working 1================================================
				if(option == 1)//
				{
					System.out.println("View available products ======");
					shop.availableProducts();					
				}	
					
			//add products to cart =========working 2==========================================
					System.out.println("Would you like to buy anything? If Yes, PRESS: 2 ");					
					option = sc.nextInt();
					
				if(option == 2 )
				{
						//get pro id
						System.out.println("Enter product ID : ");
						int pId = sc.nextInt();
						
						//search prod
						p = shop.searchProductById(pId);
						System.out.println("Enter quantity : ");
						
						//get quantity and category to buy
						int quantity = sc.nextInt();
						p.setQuantity(quantity);
						int productQuantity = p.getQuantity();
						Category pCategory = p.getCategory();
						
						//add product to cart and it will display product after added to cart					
						customer.cart.addProduct(p, productQuantity, pCategory);
						
				  }			
				
				// Itemized total : 3")===========working 3 ====================================
				System.out.println("Would you like to view total? If Yes, PRESS: 3");					
				option = sc.nextInt();				
				if(option == 3)
				{										
					//double total = customer.getCart().calculateTotal();					
					//customer.getCart().displayCart_Product();
					System.out.println("TOTAL VALUE : " + customer.getCart().calculateTotal());				
									
				}
				
				// Update/Remove Product: 4"=========================	
											
				System.out.println("Would you like to remove Product? If Yes, PRESS: 4 ");					
				option = sc.nextInt();	
				
				//display products in carts before removing from cart list
				customer.getCart().displayCart_Product();
				
				if(option == 4)
				{										
					System.out.println("Enter Product Id to Remove from Cart : ");
					int removeID = sc.nextInt();
					
					System.out.println("Enter Quantity to Remove from Cart : ");
					int removeQuantity = sc.nextInt();
					
					customer.getCart().removeProduct(removeID, removeQuantity);
					
					System.out.println("---------------------------------");
					
					System.out.println("**** REMAINING PRODUCTS IN CART ***** ");
					customer.getCart().displayCart_Product();
					System.out.println("TOTAL VALUE : " + customer.getCart().calculateTotal());
									
				}							
				
			else {
					// print options to select
						System.out.println(">> View Products/Catalog :  1");
						System.out.println(">> Add Items to cart :  2");
						System.out.println(">> Display/View cart items and Itemized total : 3");
						System.out.println(">> Update/Remove Product: 4");
						System.out.println(">> Checkout : 5");
						System.out.println(">> EXIT the Console/System : 6");
				}							

				// close scanner
				sc.close();

		}		
	}

   
				
