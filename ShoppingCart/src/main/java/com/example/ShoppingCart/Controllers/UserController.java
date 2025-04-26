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
        order.setPaidDate(orderRequest.getDate());
        // Create a SimpleDateFormat for the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Get today's date and format it to "yyyy-MM-dd" format
        String formattedDate = dateFormat.format(new Date());
        // Parse the formatted date string back into a Date object
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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



}
