package com.example.ShoppingCart.Service.impl;

import com.example.ShoppingCart.Entities.SelectedItems;
import com.example.ShoppingCart.Entities.User;
import com.example.ShoppingCart.Repository.SelectedItemsRepository;
import com.example.ShoppingCart.Repository.UserRepository;
import com.example.ShoppingCart.Service.CartItemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Qualifier
@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private SelectedItemsRepository selectedItemsRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public SelectedItems saveSelectedItems(SelectedItems selectedItems, String email) {
        Optional<User> byUserEmail = userRepository.findByEmail(email);

        if (byUserEmail.isEmpty()) {
            return null;
        }

        User user = byUserEmail.get();
        selectedItems.setUser(user); // set relationship

        // Check if item already exists for the user
        Optional<SelectedItems> existingOptional =
                selectedItemsRepository.findByUserIdAndProductId(user.getId(), selectedItems.getProductId());

        SelectedItems savedItem;

        if (existingOptional.isPresent()) {

            SelectedItems existingItem = existingOptional.get();
            if(existingItem.getQuantity()<=0){
                savedItem = existingItem;
                selectedItemsRepository.delete(existingItem);
                // Also remove from user's list (if mapped)
                if (user.getSelectedItemsList() != null) {
                    user.getSelectedItemsList().removeIf(item -> item.getId() == existingItem.getId());

                    // Optional: clear list if empty
                    if (user.getSelectedItemsList().isEmpty()) {
                        user.setSelectedItemsList(null);
                    }
                }
                return savedItem;

            }else{
                existingItem.setQuantity(existingItem.getQuantity() + selectedItems.getQuantity());
                savedItem = selectedItemsRepository.save(existingItem);
                if (user.getSelectedItemsList() == null) {
                    user.setSelectedItemsList(new ArrayList<>());
                }
                user.getSelectedItemsList().add(savedItem);
            }

        } else {
            selectedItems.setId(0);
            savedItem = selectedItemsRepository.save(selectedItems);
            if (user.getSelectedItemsList() == null) {
                user.setSelectedItemsList(new ArrayList<>());
            }

            user.getSelectedItemsList().add(savedItem);

        }

        // Ensure user's selected items list is updated
        return savedItem;
    }

    @Override
    public ArrayList<SelectedItems> getSelectedItems(String email) {
        ArrayList<SelectedItems> byEmail = (ArrayList<SelectedItems>) selectedItemsRepository.findByEmailOrderByPriceDesc(email);
        return byEmail;
    }

    @Override
    public SelectedItems deleteItem(long selectedItemId) {
        Optional<SelectedItems> byId = selectedItemsRepository.findById(selectedItemId);
        if(byId.isPresent()){
            SelectedItems selectedItems = byId.get();
            selectedItemsRepository.delete(selectedItems);
            return selectedItems;
        }
        return null;

    }




}
