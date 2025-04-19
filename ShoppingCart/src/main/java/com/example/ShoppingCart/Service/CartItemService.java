package com.example.ShoppingCart.Service;

import com.example.ShoppingCart.Entities.SelectedItems;
import com.example.ShoppingCart.Entities.User;

import java.util.ArrayList;

public interface CartItemService {

    public SelectedItems saveSelectedItems(SelectedItems selectedItems, String email);

    public ArrayList<SelectedItems> getSelectedItems(String email);

    public SelectedItems deleteItem(long selelctedItemId);

}
