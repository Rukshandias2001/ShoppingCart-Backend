package com.example.ShoppingCart.Controllers;

import com.example.ShoppingCart.Entities.Clothing;
import com.example.ShoppingCart.Entities.Electronic;
import com.example.ShoppingCart.Entities.Product;
import com.example.ShoppingCart.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/addElectronics")
    public ResponseEntity<?> addElectronic(
            @RequestPart MultipartFile imageFile,
            @RequestParam String name,
            @RequestParam String quantity,
            @RequestParam String description,
            @RequestParam String warranty,
            @RequestParam String brand,
            @RequestParam String price,
            @RequestParam String categoryId

    ) {
        try{
            Electronic electronic = new Electronic();
            electronic.setName(name);
            electronic.setQuantity(Integer.parseInt(quantity));
            electronic.setDescription(description);
            electronic.setWarranty(warranty);
            electronic.setBrand(brand);
            electronic.setPrice(Double.parseDouble(price));
            electronic.setCategoryId(Integer.parseInt(categoryId));
            String success = managerService.addElectronics(electronic, imageFile);
            return  ResponseEntity.ok().body(success);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/addClothing")
    public ResponseEntity<?> addClothing(
                                         @RequestPart MultipartFile imageFile,
                                         @RequestParam String name,
                                         @RequestParam String quantity,
                                         @RequestParam String description,
                                         @RequestParam String price,
                                         @RequestParam String categoryId,
                                         @RequestParam String colour,
                                         @RequestParam String size

    ) {
        try{
            Clothing clothing = new Clothing();
            clothing.setName(name);
            clothing.setQuantity(Integer.parseInt(quantity));
            clothing.setDescription(description);
            clothing.setColour(colour);
            clothing.setSize(size);
            clothing.setPrice(Double.parseDouble(price));
            clothing.setCategoryId(Integer.parseInt(categoryId));
            String success = managerService.addClothing(clothing, imageFile);
            return  ResponseEntity.ok().body(success);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @GetMapping("/GetAllProducts")
    public ResponseEntity<?> getAllProducts() {
        List<Product> allProducts = managerService.getAllProducts();
        return  ResponseEntity.ok().body(allProducts);

    }

    @GetMapping("getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        Product productById = managerService.getProductById(id);
        return  ResponseEntity.ok().body(productById);
    }





}
