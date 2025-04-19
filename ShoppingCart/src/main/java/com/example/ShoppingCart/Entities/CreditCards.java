package com.example.ShoppingCart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="credit_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id")
    private int id;

    @Column(name="name_of_card")
    private String name;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="expiryDate")
    private String expiryDate;

    @Column(name="cvv")
    private String cvv;


    @OneToMany(mappedBy = "creditCardNumber", fetch = FetchType.EAGER) // Correct field name
    @JsonIgnore
    private List<Orders> orders;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
