package com.example.ShoppingCart.DTO;

public class ProductRevenueDTO {
    private double totalPrice;
    private String product_type;
    private int monthly_revenue;

    public ProductRevenueDTO(double totalPrice, String product_type, int monthly_revenue) {
        this.totalPrice = totalPrice;
        this.product_type = product_type;
        this.monthly_revenue = monthly_revenue;
    }

    public ProductRevenueDTO() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getMonthly_revenue() {
        return monthly_revenue;
    }

    public void setMonthly_revenue(int monthly_revenue) {
        this.monthly_revenue = monthly_revenue;
    }
}
