package com.example.ShoppingCart.Service;

import com.example.ShoppingCart.Entities.*;

import java.util.ArrayList;

public interface OrderService {
    public Orders saveOrder(Orders order, ArrayList<Product> listOfProductsIds, String creditCardId, User customer, ArrayList<OrderedList> orderedLists);

    public ArrayList<Country> getCountryList();

    public ArrayList<State> getStateList(int countryId);

    public Orders getRecieptOrder(String email);

}
