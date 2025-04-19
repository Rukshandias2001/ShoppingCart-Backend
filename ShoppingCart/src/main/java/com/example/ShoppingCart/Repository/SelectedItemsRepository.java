package com.example.ShoppingCart.Repository;

import com.example.ShoppingCart.Entities.SelectedItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SelectedItemsRepository extends JpaRepository<SelectedItems, Long> {

    List<SelectedItems> findByUserId(int user_id);

    Optional<SelectedItems> findByUserIdAndProductId(int user_id, long productId);

    List<SelectedItems> findByEmailOrderByPriceDesc(String email);





}
