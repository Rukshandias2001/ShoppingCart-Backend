package com.example.ShoppingCart.DTO;

public class ProductDTO {

    private Long numberOfProducts;
    private Long productId;

    private String productName;


    ProductDTO(){

    }
    public ProductDTO(Long soldCount, Long productId, String productName){
        this.numberOfProducts = soldCount;
        this.productId = productId;
        this.productName = productName;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Long numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }



}
