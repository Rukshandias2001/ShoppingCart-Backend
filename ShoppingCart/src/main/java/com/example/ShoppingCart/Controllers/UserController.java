package com.example.ShoppingCart.Controllers;

import com.example.ShoppingCart.DTO.OrderRequestDTO;
import com.example.ShoppingCart.Entities.*;
import com.example.ShoppingCart.Service.CartItemService;
import com.example.ShoppingCart.Service.OrderService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    OrderService orderService;

    @PostMapping("/saveItem")
    public ResponseEntity<?> saveSelectedItems(@RequestBody SelectedItems selectedItems) {
        SelectedItems savedItem = cartItemService.saveSelectedItems(selectedItems, selectedItems.getEmail());
        return ResponseEntity.ok(savedItem);
    }

    @PostMapping("/getSelectedItems")
    public ResponseEntity<?> getSelectedItems(@RequestParam(defaultValue = "") String email) {
        ArrayList<SelectedItems> selectedItems = cartItemService.getSelectedItems(email);
        return ResponseEntity.ok(selectedItems);

    }

    @DeleteMapping("/deleteSelectedItem/{id}")
    public ResponseEntity<?> deleteSelectedItem(@PathVariable int id) {
        SelectedItems selectedItems = cartItemService.deleteItem(id);
        return ResponseEntity.ok(selectedItems);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequestDTO orderRequest) {
        Orders order = new Orders();
        order.setDate(orderRequest.getExpiryDate());
        order.setPrice(orderRequest.getPrice());
        order.setCvv(orderRequest.getCvv());
        order.setCardType(orderRequest.getCardType());
        // Create a DateTimeFormatter for date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

// Get current date and time and format it
        String formattedDate = LocalDateTime.now().format(formatter);

// Parse the formatted date string back into a Date object (if needed)
        Date parsedDate = Date.from(LocalDateTime.parse(formattedDate, formatter)
                .atZone(ZoneId.systemDefault())
                .toInstant());

        order.setDate(parsedDate);

        CreditCards creditCards = new CreditCards();
        creditCards.setExpiryDate(String.valueOf(orderRequest.getExpiryDate()));
        creditCards.setName(orderRequest.getNameOfCard());
        creditCards.setCardNumber(orderRequest.getCreditCardNumber());

        User user = new User();
        user.setUserName(orderRequest.getUserName());
        user.setLastName(orderRequest.getLastName());
        user.setEmail(orderRequest.getEmail());

        Orders orders = orderService.saveOrder(order, (ArrayList<Product>) orderRequest.getListOfProducts(), orderRequest.getCreditCardNumber(), user, (ArrayList<OrderedList>) orderRequest.getOrderList());
        return ResponseEntity.ok(orders);

    }

    @GetMapping("/getCountries")
    public ResponseEntity<?> getCountries() {
        ArrayList<Country> countryList = orderService.getCountryList();
        return ResponseEntity.ok(countryList);
    }

    @GetMapping("/GetStatesById/{id}")
    public ResponseEntity<?> getStatesById(@PathVariable int id) {
        ArrayList<State> stateList = orderService.getStateList(id);
        return ResponseEntity.ok(stateList);
    }


    @PostMapping("/GetReciept")
    public ResponseEntity<?> getRecieptById(@RequestParam(defaultValue = "") String email) {
        return ResponseEntity.ok(orderService.getRecieptOrder(email));
    }




}
