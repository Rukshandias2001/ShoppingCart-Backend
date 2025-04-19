package com.example.ShoppingCart.Repository;


import com.example.ShoppingCart.Entities.OrderedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends JpaRepository<OrderedList,Integer> {
}
