package com.example.ShoppingCart.Service.impl;

import com.example.ShoppingCart.DTO.*;
import com.example.ShoppingCart.Entities.User;
import com.example.ShoppingCart.Repository.OrderRepository;
import com.example.ShoppingCart.Repository.ProductRepository;
import com.example.ShoppingCart.Repository.UserRepository;
import com.example.ShoppingCart.Service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Qualifier
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;



    @Override
    public ArrayList<Object> getMonthlyRevenue() {
        return null;
    }

    @Override
    public ArrayList<Object> getEachProductMonthlyRevenue() {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getTopCustomers() {
        return orderRepository.getBestCustomerList();
    }

    @Override
    public Integer getNumberOfCustomers() {
        return 0;
    }

    @Override
    public DashBoardRevenueDTO getDashBoardRevenue() {
        Integer numberOfOrders = orderRepository.findOrdersSum();
        Double totalPrice = orderRepository.findOrdersSumPrice();
        Integer customers = orderRepository.findOrdersSumDistinct();
        Double totalExpeneses =productRepository.findTotalExpensesForProducts();

        DashBoardRevenueDTO dashBoardRevenueDTO = new DashBoardRevenueDTO();
        dashBoardRevenueDTO.setTotalRevenue(totalExpeneses);
        dashBoardRevenueDTO.setTotalOrders(numberOfOrders);
        dashBoardRevenueDTO.setTotalCustomers(customers);
        dashBoardRevenueDTO.setTotalSales(totalPrice);

        return dashBoardRevenueDTO;
    }

    @Override
    public ArrayList<ProductDTO> getSoldProducts() {
        ArrayList<ProductDTO> productDTOS = productRepository.numberOfSoldProducts();
        return productDTOS;
    }

    public ArrayList<ProductDTO> getSoldClothing(){
        ArrayList<ProductDTO> productDTOS = productRepository.numberOfSoldProductsForClothing();
        return productDTOS;

    }

    @Override
    public ArrayList<MonthlyIncomeDTO> getMonthlyIncome() {
        ArrayList<MonthlyIncomeDTO> monthlyIncomeList = orderRepository.getMonthlyIncomeList();
        for (MonthlyIncomeDTO monthlyIncomeDTO : monthlyIncomeList) {

        }
        return monthlyIncomeList;
    }


    public ArrayList<ProductRevenueDTO> getIncomeSalesForElectronics(String type){
        ArrayList<ProductRevenueDTO> productRevenueDTOS = productRepository.numberOfSoldProductRevenues(type);
        return  productRevenueDTOS;
    }

    @Override
    public ArrayList<ProductRevenueDTO> getIncomeSalesForClothing(String type) {
        ArrayList<ProductRevenueDTO> productRevenueDTOS = productRepository.numberOfSoldProductRevenues(type);
        return productRevenueDTOS;
    }


}
