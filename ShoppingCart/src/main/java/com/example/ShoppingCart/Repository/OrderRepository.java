package com.example.ShoppingCart.Repository;

import com.example.ShoppingCart.Entities.Orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.orderId = :orderId AND o.user.id = :userId")
    Orders findOrderByOrderIdAndUserId(@Param("orderId") int orderId, @Param("userId") int userId);

    @Query("Select o From Orders o WHERE o.user.id=:userId")
    Page<Orders> findOrdersByUserId(int userId, Pageable pageable);

    @Query("SELECT o,u.email from Orders o JOIN User u ON o.user.id = u.id order by o.orderId  Desc limit  3")
    List<Orders> findOrdersDesc();




}
