package com.example.ShoppingCart.Repository;

import com.example.ShoppingCart.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
