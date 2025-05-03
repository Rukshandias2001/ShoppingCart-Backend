package com.example.ShoppingCart.Controllers;

import com.example.ShoppingCart.DTO.CustomerDTO;
import com.example.ShoppingCart.DTO.DashBoardRevenueDTO;
import com.example.ShoppingCart.DTO.MonthlyIncomeDTO;
import com.example.ShoppingCart.DTO.ProductDTO;
import com.example.ShoppingCart.Service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/dashBoard")
public class DashBoardController {
    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping("/totalsForDashBoard")
    public ResponseEntity<?> getTotalsForSpecificDashboard(){
        DashBoardRevenueDTO dashBoardRevenue = dashBoardService.getDashBoardRevenue();
        return ResponseEntity.ok(dashBoardRevenue);
    }

    @GetMapping("/topCustomers")
    public ResponseEntity<?> getTopCustomers(){
        List<CustomerDTO> customers = dashBoardService.getTopCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/getSoldProducts")
    public ResponseEntity<?> getSoldProducts(){
        List<ProductDTO> products = dashBoardService.getSoldProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getSoldClothings")
    public ResponseEntity<?> getSoldClothing(){
        List<ProductDTO> products = dashBoardService.getSoldClothing();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getMonthlyIncome")
    public ResponseEntity<?> getMonthlyIncome(){
        List<MonthlyIncomeDTO> monthlyIncome = dashBoardService.getMonthlyIncome();
        return ResponseEntity.ok(monthlyIncome);
    }

}
