package com.example.ShoppingCart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "created_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "price")
    private double price;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "paid_date")
    private Date paidDate;

    @ManyToMany
    @JoinTable(
            name = "product_order_table",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private CreditCards creditCardNumber;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "order")
    private List<OrderedList> orderedList;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public CreditCards getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(CreditCards creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public List<OrderedList> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(List<OrderedList> orderedList) {
        this.orderedList = orderedList;
    }
}
