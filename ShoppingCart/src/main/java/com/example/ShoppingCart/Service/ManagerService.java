package com.example.ShoppingCart.Service;

import com.example.ShoppingCart.Entities.Clothing;
import com.example.ShoppingCart.Entities.Electronic;
import com.example.ShoppingCart.Entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManagerService {

    public String addElectronics(Product product,MultipartFile file);

    public String addClothing(Clothing clothing,MultipartFile file);

    public Product updateProduct(Product product);

    public void deleteProduct(Product product);

    public List<Product> getAllProducts();

    public List<Electronic> getAllElectronics();

    public List<Clothing> getAllClothings();

    public Product getProductById(Long id);





}
