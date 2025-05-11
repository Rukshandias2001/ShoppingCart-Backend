package com.example.ShoppingCart.Service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ShoppingCart.Config.CloudinaryConfig;
import com.example.ShoppingCart.Entities.Clothing;
import com.example.ShoppingCart.Entities.Electronic;
import com.example.ShoppingCart.Entities.Product;
import com.example.ShoppingCart.Repository.ProductRepository;
import com.example.ShoppingCart.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Qualifier
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CloudinaryConfig cloudinaryConfig;


    @Autowired
    public void setCloudinary(CloudinaryConfig thecloudinaryConfig) {
        this.cloudinaryConfig = thecloudinaryConfig;

    }

    @Override
    public String addElectronics(Product product,MultipartFile file) {
        String cloudinaryUrl = imageUploader(file);
        product.setImageUrl(cloudinaryUrl);
        Product newElectronic = productRepository.save(product);
        return "Product saved successfully!";
    }

    @Override
    public String addClothing(Clothing clothing,MultipartFile file) {
        String cloudinaryUrl = imageUploader(file);
        clothing.setImageUrl(cloudinaryUrl);
        Clothing newClothing = productRepository.save(clothing);
        return "Product saved successfully!";
    }


    @Override
    public Product updateProduct(Product product) {
        Optional<Product> getProduct = productRepository.findById(product.getId());
        if(getProduct.isPresent()) {
            Product updatedProduct = getProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setQuantity(product.getQuantity());
            Product save = productRepository.save(updatedProduct);
            return save;

        }else{
            return null;
        }


    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public List<Electronic> getAllElectronics() {
        return List.of();
    }

    @Override
    public List<Clothing> getAllClothings() {
        return List.of();
    }

    private String imageUploader(MultipartFile file){
        try {
            Cloudinary cloudinary =  cloudinaryConfig.cloudinary();
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        return product;
    }


}
