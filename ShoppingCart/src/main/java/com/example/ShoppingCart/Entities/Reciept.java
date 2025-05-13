package com.example.ShoppingCart.Entities;

import lombok.*;

@Data
@Getter
@Setter

public class Reciept {
    private double price;
    private int id;
    private String imageUrl;
    private String productName;
    private int quantity;

    public Reciept(double price, int id, String imageUrl, String productName, int quantity) {
        this.price = price;
        this.id = id;
        this.imageUrl = imageUrl;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Reciept() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
