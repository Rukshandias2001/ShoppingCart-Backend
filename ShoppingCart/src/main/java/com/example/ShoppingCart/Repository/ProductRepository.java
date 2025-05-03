package com.example.ShoppingCart.Repository;

import com.example.ShoppingCart.DTO.ProductDTO;
import com.example.ShoppingCart.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

//    @Query(value ="select Sum(t.price) ,t.product_type from \n" +
//            "product_order_table p Inner JOIN\n" +
//            "product t On p.product_id = t.product_id \n" +
//            " group by t.product_type \n" +
//            " Union ALl \n" +
//            " SELECT SUM(t.price) AS total_price, \n" +
//            "  'Total' AS product_type \n" +
//            "FROM product_order_table p \n" +
//            "INNER JOIN product t ON p.product_id = t.product_id ;",nativeQuery = true)
//    ArrayList

}
