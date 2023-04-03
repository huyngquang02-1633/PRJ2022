/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author ADMIN
 */
public class ProductCategory {
    Products products;
    Category category;

    public ProductCategory() {
    }

    public ProductCategory(Products products, Category category) {
        this.products = products;
        this.category = category;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "products=" + products + ", category=" + category + '}';
    }
    
    
}
