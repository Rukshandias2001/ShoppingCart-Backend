package com.example.ShoppingCart.Service.impl;

import com.example.ShoppingCart.Entities.*;
import com.example.ShoppingCart.Repository.*;
import com.example.ShoppingCart.Service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Qualifier
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderListRepository orderListRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Orders saveOrder(Orders order, ArrayList<Product> listOfProducts, String creditCardId, User customer, ArrayList<OrderedList> orderList) {
        System.out.println("Starting to process order...");

        // Check if user exists
        System.out.println("Checking if user exists...");
        User user = userRepository.findByEmail(customer.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        // Ensure the user is correctly set in the order
        order.setUser(user);

        // Check if credit card exists
        System.out.println("Checking if credit card exists...");
        CreditCards creditCardsNumber = creditCardRepository.findByCardNumber(creditCardId);
        if (creditCardsNumber == null) {
            System.out.println("Credit card not found, creating new one...");
            CreditCards creditCards = new CreditCards();
            creditCards.setCardNumber(creditCardId);
            creditCardRepository.save(creditCards);
            creditCardsNumber = creditCardRepository.findByCardNumber(creditCardId);
        }

        // Set the credit card and product list in the order
        order.setCreditCardNumber(creditCardsNumber);
        order.setProduct(listOfProducts);

        // Clear any previous order references from the orderedList
        ArrayList<OrderedList> newOrderList = new ArrayList<>();
        for (OrderedList orderedList : orderList) {
            OrderedList newOrderedList = new OrderedList();
            newOrderedList.setProductName(orderedList.getProductName());
            newOrderedList.setImageUrl(orderedList.getImageUrl());
            newOrderedList.setType(orderedList.getType());
            newOrderedList.setPrice(orderedList.getPrice());
            newOrderedList.setQuantity(orderedList.getQuantity());
            newOrderedList.setDescription(orderedList.getDescription());
            newOrderedList.setCategoryId(orderedList.getCategoryId());
            newOrderedList.setOrder(order); // Set the new order reference
            newOrderList.add(newOrderedList);
        }

        order.setOrderedList(newOrderList);

        // Save order
        System.out.println("Saving order...");
        Orders savedOrder = orderRepository.save(order);

        for (OrderedList orderedList : savedOrder.getOrderedList()) {
            orderedList.setOrder(savedOrder);
            orderListRepository.save(orderedList);
        }

        // Update user's orders list
        List<Orders> orders = user.getOrdersList();
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
        user.setOrdersList(orders);
        userRepository.save(user);

        // Update credit card's orders list
        List<Orders> creditCardOrders = creditCardsNumber.getOrders();
        if (creditCardOrders == null) {
            creditCardOrders = new ArrayList<>();
        }
        creditCardOrders.add(order);
        creditCardsNumber.setOrders(creditCardOrders);
        creditCardRepository.save(creditCardsNumber);

        // Update products' orders list
        for (Product product : order.getProduct()) {
            Product getProduct = productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            List<Orders> productOrders = getProduct.getOrdersList();
            if (productOrders == null) {
                productOrders = new ArrayList<>();
            }
            productOrders.add(order);
            getProduct.setOrdersList(productOrders);
            productRepository.save(getProduct);
        }

        System.out.println("Order processed successfully.");
        upateProduct(orderList,savedOrder.getOrderId());
        return savedOrder;
    }

    public ArrayList<Country> getCountryList() {
        String query = "SELECT  c FROM Country c";
        List<Country> countryList = entityManager.createQuery(query, Country.class).getResultList();
        return (ArrayList<Country>) countryList;
    }

    public ArrayList<State> getStateList(int countryId) {
        String query = "Select s From State s where s.country.id = " + countryId;
        List<State> stateList = entityManager.createQuery(query, State.class).getResultList();
        return (ArrayList<State>) stateList;
    }




    private void upateProduct(ArrayList<OrderedList> orderList,int orderId) {
        orderList.forEach((data)->{
            String sql = "select  Distinct s.* from product_order_table p INNER JOIN `Order` o  ON o.order_id = p.order_id INNER JOIN product s ON p.product_id = s.product_id where  s.url='" +data.getImageUrl()+ "' AND o.order_id =" +orderId;
            List <Object[]> resultList = entityManager.createNativeQuery(sql).getResultList();
            for (Object[] objects : resultList) {
                Long productId = (Long) objects[2];
                Product product = productRepository.findById(productId).get();
                int quantity = product.getQuantity();
                quantity = quantity - data.getQuantity();
                product.setQuantity(quantity);
                productRepository.save(product);
            }
        });
    }
}
