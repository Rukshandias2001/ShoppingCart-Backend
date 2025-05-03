package com.example.ShoppingCart.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class CustomerDTO {
    private String email;
    private String name;
    private double price;


    public CustomerDTO(String email, String name, double price) {
        this.email = email;
        this.name = name;
        this.price = price;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
