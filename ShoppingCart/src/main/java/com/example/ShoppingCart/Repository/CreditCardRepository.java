package com.example.ShoppingCart.Repository;

import com.example.ShoppingCart.Entities.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCards,Long> {

        CreditCards findByCardNumber(String cardNumber);

}
