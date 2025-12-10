package com.pluralsight.NorthwindTradersAPI3.models;

public class Product {
    private int productId;

    public Product(int productId) {
        this.productId = productId;

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                '}';
    }
}

