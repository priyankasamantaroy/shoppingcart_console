package com.consoleapp;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/*
 * Interface for classes that manage products in the shop.
 * Provides default varargs add  a date formatter helper.
 */
public interface ProductManager {
    void addProduct(Product product);

    // Add multiple products using varargs (convenience)
    default void addProducts(Product... products) {
        for (Product p : products) {
            addProduct(p);
        }
    }

    // Return products matching the given predicate
    List<Product> filterProducts(Predicate<Product> predicate);

    // Format a LocalDate to a simple string
    static String formatDate(LocalDate date) {
        return date.toString();
    }

    // Private helpers may be used by default methods if needed (kept simple)
    private boolean isValid(Product p) {
        return p != null && p.getId() > 0;
    }
}
