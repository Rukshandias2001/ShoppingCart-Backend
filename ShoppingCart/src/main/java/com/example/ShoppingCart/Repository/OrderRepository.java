package com.example.ShoppingCart.Repository;

import com.example.ShoppingCart.DTO.CustomerDTO;
import com.example.ShoppingCart.DTO.MonthlyIncomeDTO;
import com.example.ShoppingCart.Entities.Orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Query(value = "select * from `order` where user_id =:userId order by created_date Desc Limit 1 ",nativeQuery = true)
    Orders findOrdersByUserId(int userId);

    @Query("select count(o.orderId) from Orders o ")
    Integer findOrdersSum();

    @Query(" select count(distinct o.user.id) from Orders o ")
    Integer findOrdersSumDistinct();

    @Query("select sum(o.price) from Orders o")
    Double findOrdersSumPrice();

    @Query(value = "SELECT u.name,u.email, Sum(o.price) as \"total_price\"  from `order` o Inner JOIN `user` u ON u.user_id = o.user_id  \n" +
            "group by o.user_id Order by total_price Desc Limit 3 ",nativeQuery = true)
    ArrayList<CustomerDTO> getBestCustomerList();

    @Query(value="select Year(created_date) as year,MONTH(created_date) as month,Sum(price) as monthly_price  from `order` \n" +
            "where created_date is not null\n" +
            "group by Year(created_date),MONTH(created_date)\n" +
            "order by year,month ",nativeQuery = true)
    ArrayList<MonthlyIncomeDTO> getMonthlyIncomeList();


    @Query(value = "select * from `order` where user_id =:userId ",nativeQuery = true)
    Page<Orders> getOrdersByUserId(@Param("userId") int userId,Pageable pageable);






}
