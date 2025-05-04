package com.example.ShoppingCart.Repository;

import com.example.ShoppingCart.DTO.ProductDTO;
import com.example.ShoppingCart.DTO.ProductRevenueDTO;
import com.example.ShoppingCart.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select Sum(p.price) from Product p ")
    double findTotalExpensesForProducts();

    @Query(value = "select Count(order_id) ,p.product_id,t.name from \n" +
            "product_order_table p Inner JOIN\n" +
            "product t On p.product_id = t.product_id where t.product_type = \"Electronic\"\n" +
            " group by p.product_id;",nativeQuery = true)
    ArrayList<ProductDTO> numberOfSoldProducts();

    @Query(value = "select Count(order_id) ,p.product_id,t.name from \n" +
            "product_order_table p Inner JOIN\n" +
            "product t On p.product_id = t.product_id where t.product_type = \"Clothing\"\n" +
            " group by p.product_id;",nativeQuery = true)
    ArrayList<ProductDTO> numberOfSoldProductsForClothing();

    @Query(value = "SELECT SUM(t.price) AS totalPrice, t.product_type, MONTH(o.created_date) AS month " +
            "FROM product_order_table p " +
            "INNER JOIN product t ON p.product_id = t.product_id " +
            "INNER JOIN `order` o ON o.order_id = p.order_id " +
            "WHERE t.product_type = :productType " +
            "GROUP BY t.product_type, YEAR(o.created_date), MONTH(o.created_date)", nativeQuery = true)
    ArrayList<ProductRevenueDTO> numberOfSoldProductRevenues(@Param("productType") String productType);




}
