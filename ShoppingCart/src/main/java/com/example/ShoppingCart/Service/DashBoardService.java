package com.example.ShoppingCart.Service;

import com.example.ShoppingCart.DTO.CustomerDTO;
import com.example.ShoppingCart.DTO.DashBoardRevenueDTO;
import com.example.ShoppingCart.DTO.MonthlyIncomeDTO;
import com.example.ShoppingCart.DTO.ProductDTO;

import java.util.ArrayList;

public interface DashBoardService {

    public ArrayList<Object> getMonthlyRevenue();

    public ArrayList<Object> getEachProductMonthlyRevenue();

    public ArrayList<CustomerDTO> getTopCustomers();

    public Integer getNumberOfCustomers();

    public DashBoardRevenueDTO getDashBoardRevenue();

    public ArrayList<ProductDTO> getSoldProducts();

    public ArrayList<ProductDTO> getSoldClothing();

    public ArrayList<MonthlyIncomeDTO> getMonthlyIncome();

}
